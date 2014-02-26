package be.degreyt.byndr.services;

import be.degreyt.byndr.datamodel.Expansion;
import be.degreyt.byndr.datamodel.Rarity;

import java.util.Set;

public interface CardCollection {

    Set<CardOwnership> ownerships();

    int totalWildCardCost();

    int totalWildcardCost(Rarity rarity);

    CardCollection subSet(Expansion expansion);
}
