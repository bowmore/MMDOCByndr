package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.datamodel.*;

import java.util.Set;

/**
 * Created by WDH on 10/03/14.
 */
public class CardSearchFilterImpl implements be.degreyt.mmdoc.byndr.services.CardSearchFilter {
    Set<CardType> cardTypes;
    Set<Expansion> expansions;
    Set<Rarity> rarities;
    Set<Faction> factions;
    Set<MagicSchool> magicSchools;

    public CardSearchFilterImpl() {}

    @Override
    public void filterOnCardType(Set<CardType> cardTypes) {
        this.cardTypes = cardTypes;
    }

    @Override
    public void filterOnExpansion(Set<Expansion> expansions) {
        this.expansions = expansions;
    }

    @Override
    public void filterOnRarity(Set<Rarity> rarities) {
        this.rarities = rarities;
    }

    @Override
    public void filterOnFaction(Set<Faction> factions) {
        this.factions = factions;
    }

    @Override
    public void filterOnMagicSchool(Set<MagicSchool> magicSchools) {
        this.magicSchools = magicSchools;
    }

    @Override
    public void disableFilterOnCardType() {
        this.cardTypes = null;
    }

    @Override
    public void disableFilterOnExpansion() {
        this.expansions = null;
    }

    @Override
    public void filterOnRarity() {
        this.rarities = null;
    }

    @Override
    public void disableFilterOnFaction() {
        this.factions = null;
    }

    @Override
    public void disableFilterOnMagicSchool() {
        this.magicSchools = null;
    }

    Set<CardType> getCardTypes() {
        return cardTypes;
    }

    Set<Expansion> getExpansions() {
        return expansions;
    }

    Set<Rarity> getRarities() {
        return rarities;
    }

    Set<Faction> getFactions() {
        return factions;
    }

    Set<MagicSchool> getMagicSchools() {
        return magicSchools;
    }
}
