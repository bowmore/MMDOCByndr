package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.datamodel.Faction;

import java.util.Set;

/**
 * Created by WDH on 11/03/14.
 */
class FactionFilter extends CardOwnershipAttributeFilter<Faction> {
    FactionFilter(Set<Faction> validValues) {
        super(validValues);
    }

    FactionFilter(Faction... validValues) {
        super(validValues);
    }

    @Override
    public boolean test(CardOwnership cardOwnership) {
        return validValues().contains(cardOwnership.getCard().getFaction());

    }
}
