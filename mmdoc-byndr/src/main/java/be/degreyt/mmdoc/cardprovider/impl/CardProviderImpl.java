package be.degreyt.mmdoc.cardprovider.impl;

import be.degreyt.mmdoc.cardprovider.CardProvider;
import be.degreyt.mmdoc.datamodel.*;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by WDH on 09/03/14.
 */
class CardProviderImpl implements CardProvider {
    private List<Card> cards = new ArrayList<>();

    public CardProviderImpl() {
    }
    public CardProviderImpl(List<Card> cards) {
        this.cards.addAll(cards);
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void add(Iterable<Card> cards) {
        cards.forEach(this.cards::add);
    }
}
