package be.degreyt.mmdoc.ui;

import be.degreyt.mmdoc.byndr.services.ByndrService;
import be.degreyt.mmdoc.byndr.services.CardCollection;
import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.datamodel.*;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MockByndrServicesModule extends AbstractModule {

    private static final Logger LOG = Logger.getLogger(MockByndrServicesModule.class.getName());

    @Override
    protected void configure() {
        bind(ByndrService.class).to(MockByndrService.class);
    }

    private static class MockByndrService implements ByndrService {
        @Override
        public CardCollection load() {
            return new MockCardCollection();
        }
    }

    private static class MockCardCollection implements CardCollection {
        @Override
        public Set<CardOwnership> ownerships() {
            try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Paths.get(".\\mmdoc-byndr\\src\\main\\resources\\images\\small"))) {
                Iterator<Path> iterator = dirStream.iterator();
                ImmutableSet.Builder<CardOwnership> builder = ImmutableSet.builder();
                for (int i = 0; i < 100; i++) {
                    if (iterator.hasNext()) {
                        Path path = iterator.next();
                        builder.add(new CardOwnershipImpl("Card " + i, "file:./mmdoc-byndr/src/main/resources/images/small/"+path.getFileName().toString()));
                    }
                }
                return builder.build();
            } catch (IOException e) {
                return Collections.emptySet();
            }
        }

        @Override
        public int totalWildCardCost() {
            return 0;
        }

        @Override
        public int totalWildcardCost(Rarity rarity) {
            return 0;
        }
    }

    private static class CardOwnershipImpl implements CardOwnership {
        private final String name;
        private final String url;

        private CardOwnershipImpl(String name, String url) {
            this.name = name;
            this.url = url;
        }

        @Override
        public Card getCard() {
            return new CardImpl(name, url);
        }

        @Override
        public int ownedCopies() {
            return 0;
        }

        @Override
        public int wantedCopies() {
            return 0;
        }

        @Override
        public int required() {
            return 0;
        }
    }

    private static class CardImpl implements Card {
        private final String name;
        private final String url;

        private CardImpl(String name, String url) {
            this.name = name;
            this.url = url;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Faction getFaction() {
            return null;
        }

        @Override
        public Set<ExpansionInfo> getExpansionInfo() {
            return null;
        }

        @Override
        public int getWildCardCost() {
            return 0;
        }

        @Override
        public Set<Format> getAllowedFormats() {
            return null;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public Optional<URL> smallImageUrl() {
            try {
                return Optional.of(new URL(url));
            } catch (MalformedURLException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
                return Optional.absent();
            }
        }

        @Override
        public Optional<URL> largeImageUrl() {
            return Optional.absent();
        }
    }
}
