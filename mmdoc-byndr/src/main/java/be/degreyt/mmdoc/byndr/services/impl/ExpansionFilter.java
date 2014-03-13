package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.datamodel.Expansion;
import be.degreyt.mmdoc.datamodel.ExpansionInfo;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by WDH on 11/03/14.
 */
class ExpansionFilter extends CardOwnershipAttributeFilter<Expansion> {
    ExpansionFilter(Set<Expansion> validValues) {
        super(validValues);
    }

    ExpansionFilter(Expansion... validValues) {
        super(validValues);
    }

    @Override
    public boolean test(CardOwnership ownership) {
        return ownership.getCard().getExpansionInfo().stream().map(ExpansionInfo::getExpansion).anyMatch(validValues()::contains);
    }
}
