package be.degreyt.mmdoc.byndr.services;

import be.degreyt.mmdoc.datamodel.Expansion;
import be.degreyt.mmdoc.datamodel.Rarity;

import java.util.Set;

public interface CardCollection {

    Set<CardOwnership> ownerships();

    int totalWildCardCost();

    int totalWildcardCost(Rarity rarity);

    CardCollection subSet(Expansion expansion);
}
