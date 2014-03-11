package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.byndr.services.CardOwnershipAttributeSet;
import be.degreyt.mmdoc.byndr.services.CardOwnershipCriterium;

import java.util.Set;

/**
 * Created by WDH on 11/03/14.
 */
abstract class CardOwnershipAttributeSetCriterium<T> implements CardOwnershipCriterium {
    private CardOwnershipAttributeSet<T> cardOwnershipAttributeSet;
    private Set<T> validValues;

    protected CardOwnershipAttributeSetCriterium(CardOwnershipAttributeSet<T> cardOwnershipAttributeSet, Set<T> validValues) {
        this.cardOwnershipAttributeSet = cardOwnershipAttributeSet;
        this.validValues = validValues;
    }

    @Override
    public boolean test(CardOwnership cardOwnership) {
        Set<T> attributeSet = cardOwnershipAttributeSet.apply(cardOwnership);
        return validValues.stream().anyMatch(attributeSet::contains);
    }
}
