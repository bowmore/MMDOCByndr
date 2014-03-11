package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnerShipAttribute;
import be.degreyt.mmdoc.datamodel.CardType;

import java.util.Set;

/**
 * Created by WDH on 11/03/14.
 */
public class CardTypeCriterium extends CardOwnershipAttributeCriterium<CardType> {
    protected CardTypeCriterium(Set<CardType> validValues) {
        super(co -> CardType.forClass(co.getCard().getClass()), validValues);
    }
}
