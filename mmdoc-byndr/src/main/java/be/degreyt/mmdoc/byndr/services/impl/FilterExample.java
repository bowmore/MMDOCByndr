package be.degreyt.mmdoc.byndr.services.impl;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.datamodel.Expansion;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Created by WDH on 11/03/14.
 */
public class FilterExample {

    public static void main(String[] args) {
        ArrayList<CardOwnership> cardOwnerships = new ArrayList<>();
        cardOwnerships.stream().filter(new ExpansionFilter(EnumSet.of(Expansion.BASE_SET, Expansion.FIVE_TOWERS))).map(co -> co.getCard().getName()).forEach(System.out::println);
    }
}
