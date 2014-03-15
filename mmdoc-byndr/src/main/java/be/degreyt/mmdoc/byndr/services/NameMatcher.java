package be.degreyt.mmdoc.byndr.services;

import java.util.function.Predicate;

public final class NameMatcher implements Predicate<CardOwnership> {

    private String text;

    public NameMatcher(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean test(CardOwnership ownership) {
        return ownership.getCard().getName().toLowerCase().contains(text.toLowerCase());
    }
}
