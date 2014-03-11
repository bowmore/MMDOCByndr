package be.degreyt.mmdoc.byndr.services;

import java.util.Set;
import java.util.function.Function;

/**
 * Created by WDH on 11/03/14.
 */
public interface CardOwnershipAttributeSet<T> extends Function<CardOwnership, Set<T>> {
}
