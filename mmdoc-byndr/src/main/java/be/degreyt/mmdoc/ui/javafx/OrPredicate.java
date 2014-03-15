package be.degreyt.mmdoc.ui.javafx;

import be.degreyt.mmdoc.byndr.services.CardOwnership;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

class OrPredicate implements Predicate<CardOwnership> {

    private Set<Predicate<CardOwnership>> activated = new HashSet<>();

    public boolean add(Predicate<CardOwnership> predicate) {
        return activated.add(predicate);
    }

    public boolean remove(Predicate<CardOwnership> predicate) {
        return activated.remove(predicate);
    }

    @Override
    public boolean test(CardOwnership ownership) {
        if (activated.isEmpty()) {
            return true;
        }
        return activated.stream().reduce(false, (aBoolean, cardOwnershipPredicate) -> aBoolean || cardOwnershipPredicate.test(ownership), (aBoolean, aBoolean2) -> aBoolean || aBoolean2);
    }
}
