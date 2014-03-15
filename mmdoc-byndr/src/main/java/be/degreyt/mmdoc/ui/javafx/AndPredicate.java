package be.degreyt.mmdoc.ui.javafx;

import be.degreyt.mmdoc.byndr.services.CardOwnership;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

class AndPredicate implements Predicate<CardOwnership> {

    private Set<Predicate<CardOwnership>> activated = new HashSet<>();

    AndPredicate(Collection<Predicate<CardOwnership>> activated) {
        this.activated.addAll(activated);
    }

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
        return activated.stream().reduce(true, (passes, filter) -> passes && filter.test(ownership), (a, b) -> a && b);
    }
}
