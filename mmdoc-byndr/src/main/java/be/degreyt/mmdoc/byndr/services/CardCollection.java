package be.degreyt.mmdoc.byndr.services;

import be.degreyt.mmdoc.datamodel.Expansion;
import be.degreyt.mmdoc.datamodel.Rarity;

import java.util.Set;
import java.util.function.Predicate;

public interface CardCollection {

    Set<CardOwnership> ownerships();

    Set<CardOwnership> ownerships(Predicate<CardOwnership> filter);

    int totalWildCardCost();

    int totalWildcardCost(Rarity rarity);

}
