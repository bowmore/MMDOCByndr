package be.degreyt.byndr.datamodel;

import java.util.Set;

public interface Card {
    String getName();
    Faction getFaction();
    Set<ExpansionInfo> getExpansionInfo();
    int getTotalNumberOfCopiesOwned();
    int getTotalNumberOfCopiesWanted();
    int getNumberOfCopiesOwned();
    int getNumberOfCopiesWanted();
    int setNumberOfCopiesOwned();
    int setNumberOfCopiesWanted();
    int getWildCardCost();
}
