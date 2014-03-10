package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.datamodel.Card;

class CardOwnershipImpl implements CardOwnership {

    private final Card card;
    private int owned;
    private int wanted;

    CardOwnershipImpl(Card card, int owned, int wanted) {
        this.card = card;
        this.owned = owned;
        this.wanted = wanted;
    }

    @Override
    public Card getCard() {
        return card;
    }

    @Override
    public int ownedCopies() {
        return owned;
    }

    @Override
    public int wantedCopies() {
        return wanted;
    }

    @Override
    public int required() {
        return Math.max(0, wanted - owned);
    }

}
