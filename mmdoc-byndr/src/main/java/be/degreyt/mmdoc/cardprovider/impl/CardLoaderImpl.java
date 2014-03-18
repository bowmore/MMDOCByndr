package be.degreyt.mmdoc.cardprovider.impl;

import be.degreyt.mmdoc.cardprovider.CardLoader;
import be.degreyt.mmdoc.cardprovider.CardParser;
import be.degreyt.mmdoc.cardprovider.CardProvider;
import be.degreyt.mmdoc.cardprovider.DataFileEnumerator;
import be.degreyt.mmdoc.datamodel.*;
import be.degreyt.mmdoc.exceptions.MissingExpansionCode;
import be.degreyt.mmdoc.exceptions.UnderlyingIOException;
import be.degreyt.mmdoc.exceptions.UnderlyingUrlException;
import com.google.inject.Provider;

import javax.inject.Inject;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WDH on 09/03/14.
 */
class CardLoaderImpl implements CardLoader {

    private static final String ROOT_PATH = ".\\mmdoc-byndr\\src\\main\\resources\\gamedata"; // TODO dynamic load of path
    private final Pattern FILE_NAME_PATTERN = Pattern.compile("cards_(\\w{3})(_\\w)?\\.xml");

    private final Provider<CardBuilder> cardBuilderProvider;
    private final DataFileEnumerator dataFileEnumerator;
    private final CardParser cardParser;

    @Inject
    public CardLoaderImpl(Provider<CardBuilder> cardBuilderProvider, DataFileEnumerator dataFileEnumerator, CardParser cardParser) {
        this.cardBuilderProvider = cardBuilderProvider;
        this.dataFileEnumerator = dataFileEnumerator;
        this.cardParser = cardParser;
    }

    @Override
    public CardProvider loadCards() {
        CardProviderImpl cardProvider = new CardProviderImpl();
        dataFileEnumerator.dataFiles(ROOT_PATH).parallel().map(this::getCards).forEach(cardProvider::add);
        return cardProvider;
    }

    private Function<? super Future<List<Card>>, List<Card>> futureGet() {
        return listFuture -> {
            try {
                return listFuture.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return Collections.emptyList();
            } catch (ExecutionException e) {
                throw (RuntimeException) e.getCause();
            }
        };
    }

    private List<Card> getCards(File file) {
        try(InputStream inputStream = new FileInputStream(file)) {
            return cardParser.parse(inputStream, getExpansion(file).orElse(null));
        } catch (IOException e) {
            throw new UnderlyingIOException(e);
        }
    }

    private Optional<Expansion> getExpansion(File file) {
        return getExpansionCode(file).map(s -> Expansion.forCode(s).get());
    }

    private Optional<String> getExpansionCode(File file) {
        Matcher matcher = FILE_NAME_PATTERN.matcher(file.getName());
        return matcher.matches() ? Optional.of(matcher.group(1)) : Optional.<String>empty();
    }

}
