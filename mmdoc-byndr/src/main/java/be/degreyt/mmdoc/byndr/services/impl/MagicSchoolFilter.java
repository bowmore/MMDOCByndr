package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.datamodel.HasMagicSchools;
import be.degreyt.mmdoc.datamodel.MagicSchool;
import be.degreyt.mmdoc.datamodel.Spell;

import java.util.Set;

/**
 * Created by WDH on 11/03/14.
 */
class MagicSchoolFilter extends CardOwnershipAttributeFilter<MagicSchool> {
    MagicSchoolFilter(Set<MagicSchool> validValues) {
        super(validValues);
    }

    MagicSchoolFilter(MagicSchool... validValues) {
        super(validValues);
    }

    @Override
    public boolean test(CardOwnership cardOwnership) {
        if (cardOwnership.getCard() instanceof HasMagicSchools) {
            HasMagicSchools card = (HasMagicSchools) cardOwnership.getCard();
            return validValues().stream().anyMatch(card.getMagicSchools()::contains);
        }
        return false;

    }
}
