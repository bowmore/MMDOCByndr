package be.degreyt.mmdoc.datamodel;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by WDH on 26/02/14.
 */
public enum Format {
    OPEN(EnumSet.allOf(Expansion.class)), STANDARD(EnumSet.of(Expansion.BASE_SET_2));

    Format(Set<Expansion> includedExpansions) {
        this.includedExpansions = includedExpansions;
    }

    Set<Expansion> includedExpansions;
}
