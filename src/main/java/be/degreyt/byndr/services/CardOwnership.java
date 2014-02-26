package be.degreyt.byndr.services;

import be.degreyt.byndr.datamodel.Card;

public interface CardOwnership {

    Card getCard();

    int ownedCopies();

    int wantedCopies();

    int required();


}
