package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.byndr.services.FilterProvider;
import be.degreyt.mmdoc.byndr.services.NameMatcher;
import be.degreyt.mmdoc.datamodel.*;

import java.util.function.Predicate;

public class FilterProviderImpl implements FilterProvider {
    @Override
    public Predicate<CardOwnership> hasType(CardType... cardTypes) {
        return new CardTypeFilter(cardTypes);
    }

    @Override
    public Predicate<CardOwnership> isFromExpansion(Expansion... expansions) {
        return new ExpansionFilter(expansions);
    }

    @Override
    public Predicate<CardOwnership> isFromFaction(Faction... factions) {
        return new FactionFilter(factions);
    }

    @Override
    public Predicate<CardOwnership> hasMagicSchool(MagicSchool... magicSchools) {
        return new MagicSchoolFilter(magicSchools);
    }

    @Override
    public Predicate<CardOwnership> hasRarity(Rarity... rarities) {
        return new RarityFilter(rarities);
    }

    @Override
    public Predicate<CardOwnership> and(final Predicate<CardOwnership> first, final Predicate<CardOwnership> second) {
        return ownership -> first.test(ownership) && second.test(ownership);
    }

    @Override
    public Predicate<CardOwnership> or(Predicate<CardOwnership> first, Predicate<CardOwnership> second) {
        return ownership -> first.test(ownership) || second.test(ownership);
    }

    @Override
    public Predicate<CardOwnership> xor(Predicate<CardOwnership> first, Predicate<CardOwnership> second) {
        return ownership -> first.test(ownership) ^ second.test(ownership);
    }

    @Override
    public Predicate<CardOwnership> hasExpansion(Expansion expansion) {
        return ownership -> ownership.getCard().getExpansion().equals(expansion);
    }

    @Override
    public Predicate<CardOwnership> hasFaction(Faction faction) {
        return ownership -> faction.equals(ownership.getCard().getFaction());
    }

    @Override
    public NameMatcher nameMatches(String text) {
        return new NameMatcher(text);
    }
}
