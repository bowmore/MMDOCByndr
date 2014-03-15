package be.degreyt.mmdoc.byndr.services;

import be.degreyt.mmdoc.datamodel.Card;
import be.degreyt.mmdoc.utils.ComparisonBuilder;

public interface CardOwnership extends Comparable<CardOwnership> {

    Card getCard();

    int ownedCopies();

    int wantedCopies();

    int required();

    default int compareTo(CardOwnership other) {
        return new ComparisonBuilder()
                .add(getCard(), other.getCard())
                .compare();
    }
}
