package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnerShipAttribute;
import be.degreyt.mmdoc.datamodel.MagicSchool;
import be.degreyt.mmdoc.datamodel.Spell;

import java.util.Set;

/**
 * Created by WDH on 11/03/14.
 */
public class MagicSchoolCriterium extends CardOwnershipAttributeCriterium<MagicSchool> {
    protected MagicSchoolCriterium(Set<MagicSchool> validValues) {
        super(co -> co.getCard() instanceof Spell ? ((Spell)co.getCard()).getMagicSchool() : null, validValues);
    }
}
