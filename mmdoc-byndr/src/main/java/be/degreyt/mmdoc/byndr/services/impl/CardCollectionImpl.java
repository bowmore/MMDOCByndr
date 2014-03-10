package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardCollection;
import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.datamodel.Rarity;
import com.google.common.collect.ImmutableSet;

import java.util.Collections;
import java.util.Set;

class CardCollectionImpl implements CardCollection {

    private final ImmutableSet<CardOwnership> ownerships;

    CardCollectionImpl(Set<CardOwnership> ownerships) {
        this.ownerships = ImmutableSet.copyOf(ownerships);
    }

    @Override
    public Set<CardOwnership> ownerships() {
        return ownerships;
    }

    @Override
    public int totalWildCardCost() {
        return 0; // TODO
    }

    @Override
    public int totalWildcardCost(Rarity rarity) {
        return 0; // TODO
    }
}
