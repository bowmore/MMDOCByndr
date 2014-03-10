package be.degreyt.mmdoc.byndr.services;

import be.degreyt.mmdoc.datamodel.*;

import java.util.Set;

/**
 * Created by WDH on 10/03/14.
 */
public interface CardSearchFilter {
    void filterOnCardType(Set<CardType> cardTypes);

    void filterOnExpansion(Set<Expansion> expansions);

    void filterOnRarity(Set<Rarity> rarities);

    void filterOnFaction(Set<Faction> factions);

    void filterOnMagicSchool(Set<MagicSchool> magicSchools);

    void disableFilterOnCardType();

    void disableFilterOnExpansion();

    void filterOnRarity();

    void disableFilterOnFaction();

    void disableFilterOnMagicSchool();
}
