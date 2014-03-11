package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnershipAttributeSet;
import be.degreyt.mmdoc.datamodel.Expansion;
import be.degreyt.mmdoc.datamodel.ExpansionInfo;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by WDH on 11/03/14.
 */
public class ExpansionCriterium extends CardOwnershipAttributeSetCriterium<Expansion> {
    protected ExpansionCriterium(Set<Expansion> validValues) {
        super(co -> co.getCard().getExpansionInfo().stream().map(ExpansionInfo::getExpansion).collect(Collectors.toSet()), validValues);
    }
}
