package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.ByndrService;
import be.degreyt.mmdoc.byndr.services.CardCollection;
import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.byndr.services.FilterProvider;
import be.degreyt.mmdoc.cardlibrary.CardLibrary;
import be.degreyt.mmdoc.cardlibrary.CardLoader;
import com.google.common.collect.ImmutableSet;

import javax.inject.Inject;

class ByndrServiceImpl implements ByndrService{

    private final CardLoader cardLoader;

    @Inject
    ByndrServiceImpl(CardLoader cardLoader) {
        this.cardLoader = cardLoader;
    }

    @Override
    public CardCollection load() {
        CardLibrary cardLibrary = cardLoader.loadCards();
        ImmutableSet.Builder<CardOwnership> builder = ImmutableSet.builder();
        cardLibrary.getCards().stream().map((card) -> new CardOwnershipImpl(card, 0, 0)).forEach(builder::add);
        return new CardCollectionImpl(builder.build());
    }

    @Override
    public FilterProvider getFilterProvider() {
        return new FilterProviderImpl();
    }
}
