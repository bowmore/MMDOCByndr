package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.datamodel.CardType;

import java.util.Optional;
import java.util.Set;

/**
 * Created by WDH on 11/03/14.
 */
class CardTypeFilter extends CardOwnershipAttributeFilter<CardType> {
    CardTypeFilter(Set<CardType> validValues) {
        super(validValues);
    }

    CardTypeFilter(CardType... validValues) {
        super(validValues);
    }

    @Override
    public boolean test(CardOwnership cardOwnership) {
        Optional<CardType> found = CardType.forClass(cardOwnership.getCard().getClass());
        return found.isPresent() && validValues().contains(found.get());
    }
}
