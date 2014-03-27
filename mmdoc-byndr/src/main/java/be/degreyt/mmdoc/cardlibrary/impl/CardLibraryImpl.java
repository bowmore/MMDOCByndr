package be.degreyt.mmdoc.cardlibrary.impl;

import be.degreyt.mmdoc.cardlibrary.CardLibrary;
import be.degreyt.mmdoc.datamodel.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by WDH on 09/03/14.
 */
class CardLibraryImpl implements CardLibrary {
    private List<Card> cards = new ArrayList<>();

    public CardLibraryImpl() {
    }
    public CardLibraryImpl(List<Card> cards) {
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
