package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import com.google.common.collect.ImmutableSet;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by WDH on 11/03/14.
 */
abstract class CardOwnershipAttributeFilter<T> implements Predicate<CardOwnership> {
    private final Set<T> validValues;

    CardOwnershipAttributeFilter(Set<T> validValues) {
        this.validValues = ImmutableSet.copyOf(validValues);
    }

    CardOwnershipAttributeFilter(T... validValues) {
        this.validValues = ImmutableSet.copyOf(validValues);
    }

    Set<T> validValues() {
        return validValues;
    }
}
