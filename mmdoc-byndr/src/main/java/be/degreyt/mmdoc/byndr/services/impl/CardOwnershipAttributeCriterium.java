package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnerShipAttribute;
import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.byndr.services.CardOwnershipCriterium;

import java.util.Set;

/**
 * Created by WDH on 11/03/14.
 */
abstract class CardOwnershipAttributeCriterium<T> implements CardOwnershipCriterium {
    private CardOwnerShipAttribute<T> cardOwnerShipAttribute;
    private Set<T> validValues;

    protected CardOwnershipAttributeCriterium(CardOwnerShipAttribute<T> cardOwnerShipAttribute, Set<T> validValues) {
        this.cardOwnerShipAttribute = cardOwnerShipAttribute;
        this.validValues = validValues;
    }

    @Override
    public boolean test(CardOwnership cardOwnership) {
        return validValues.contains(cardOwnerShipAttribute.apply(cardOwnership));

    }
}
