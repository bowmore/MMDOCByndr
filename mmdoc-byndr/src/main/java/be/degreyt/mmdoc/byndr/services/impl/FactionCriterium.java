package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnerShipAttribute;
import be.degreyt.mmdoc.datamodel.Faction;

import java.util.Set;

/**
 * Created by WDH on 11/03/14.
 */
public class FactionCriterium extends CardOwnershipAttributeCriterium<Faction> {
    protected FactionCriterium(Set<Faction> validValues) {
        super(co -> co.getCard().getFaction(), validValues);
    }
}
