package be.degreyt.mmdoc.byndr.services;

import be.degreyt.mmdoc.datamodel.*;

import java.util.function.Predicate;

public interface FilterProvider {

    Predicate<CardOwnership> hasType(CardType... cardTypes);

    Predicate<CardOwnership> isFromExpansion(Expansion... cardTypes);

    Predicate<CardOwnership> isFromFaction(Faction... factions);

    Predicate<CardOwnership> hasMagicSchool(MagicSchool... magicSchools);

    Predicate<CardOwnership> hasRarity(Rarity... rarities);

    Predicate<CardOwnership> and(Predicate<CardOwnership> first, Predicate<CardOwnership> second);

    Predicate<CardOwnership> or(Predicate<CardOwnership> first, Predicate<CardOwnership> second);

    Predicate<CardOwnership> xor(Predicate<CardOwnership> first, Predicate<CardOwnership> second);

    Predicate<CardOwnership> nameMatches(String text);
}
