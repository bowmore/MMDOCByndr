package be.degreyt.mmdoc.byndr.services;

import be.degreyt.mmdoc.datamodel.CardBuilder;

public interface ByndrService {

    CardCollection load();

    CardBuilder newCardBuilder();
}
