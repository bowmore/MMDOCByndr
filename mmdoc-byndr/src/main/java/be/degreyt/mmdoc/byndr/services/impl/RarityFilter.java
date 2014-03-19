package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.datamodel.ExpansionInfo;
import be.degreyt.mmdoc.datamodel.Rarity;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by WDH on 11/03/14.
 */
class RarityFilter extends CardOwnershipAttributeFilter<Rarity> {
    RarityFilter(Set<Rarity> validValues) {
        super(validValues);
    }

    RarityFilter(Rarity... validValues) {
        super(validValues);
    }

    @Override
    public boolean test(CardOwnership cardOwnership) {
        return validValues().contains(cardOwnership.getCard().getRarity());
    }
}
