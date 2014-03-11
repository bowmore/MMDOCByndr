package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnershipAttributeSet;
import be.degreyt.mmdoc.datamodel.ExpansionInfo;
import be.degreyt.mmdoc.datamodel.Rarity;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by WDH on 11/03/14.
 */
public class RarityCriterium extends CardOwnershipAttributeSetCriterium<Rarity> {
    protected RarityCriterium(Set<Rarity> validValues) {
        super(co -> co.getCard().getExpansionInfo().stream().map(ExpansionInfo::getRarity).collect(Collectors.toSet()), validValues);
    }
}
