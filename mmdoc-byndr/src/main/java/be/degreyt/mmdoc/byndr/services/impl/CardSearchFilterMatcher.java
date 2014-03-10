package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.datamodel.*;

import java.util.Set;

/**
 * Created by WDH on 10/03/14.
 */
public class CardSearchFilterMatcher {

    private CardSearchFilterImpl cardSearchFilter;

    public CardSearchFilterMatcher(CardSearchFilterImpl cardSearchFilter) {
        this.cardSearchFilter = cardSearchFilter;
    }

    public boolean matches(CardOwnership cardOwnership) {
        return matchesCardType(cardOwnership.getCard())
                && matchesExpansion(cardOwnership.getCard())
                && matchesRarity(cardOwnership.getCard())
                && matchesFaction(cardOwnership.getCard())
                && matchesMagicSchool(cardOwnership.getCard());

    }

    private boolean matchesCardType(Card card) {
        return cardSearchFilter.getCardTypes() == null ||
                cardSearchFilter.getCardTypes().contains(CardType.forClass(card.getClass()));
    }

    private boolean matchesExpansion(Card card) {
        return cardSearchFilter.getExpansions() == null ||
                containsAnyExpansion(cardSearchFilter.getExpansions(), card.getExpansionInfo());
    }

    private boolean containsAnyExpansion(Set<Expansion> expansions, Set<ExpansionInfo> expansionInfos) {
        for (ExpansionInfo expansionInfo : expansionInfos) {
            if (expansions.contains(expansionInfo.getExpansion())) {
                return true;
            }
        }
        return false;
    }

    private boolean matchesRarity(Card card) {
        return cardSearchFilter.getRarities() == null ||
                containsAnyRarity(cardSearchFilter.getRarities(), card.getExpansionInfo());
    }

    private boolean containsAnyRarity(Set<Rarity> rarities, Set<ExpansionInfo> expansionInfos) {
        for (ExpansionInfo expansionInfo : expansionInfos) {
            if (rarities.contains(expansionInfo.getRarity())) {
                return true;
            }
        }
        return false;
    }

    private boolean matchesFaction(Card card) {
        return cardSearchFilter.getFactions() == null || cardSearchFilter.getFactions().contains(card.getFaction());
    }

    private boolean matchesMagicSchool(Card card) {
        return cardSearchFilter.getMagicSchools() == null ||
                (card instanceof Spell && cardSearchFilter.getMagicSchools().contains(((Spell) card).getMagicSchool()));
    }
}
