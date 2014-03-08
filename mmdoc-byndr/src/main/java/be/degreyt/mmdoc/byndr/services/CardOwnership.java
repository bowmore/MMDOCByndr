package be.degreyt.mmdoc.byndr.services;

import be.degreyt.mmdoc.datamodel.Card;

public interface CardOwnership {

    Card getCard();

    int ownedCopies();

    int wantedCopies();

    int required();


}
