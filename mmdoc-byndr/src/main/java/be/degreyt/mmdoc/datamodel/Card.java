package be.degreyt.mmdoc.datamodel;

import java.util.Set;

public interface Card {
    String getName();
    Faction getFaction();
    Set<ExpansionInfo> getExpansionInfo();
    int getWildCardCost();
    Set<Format> getAllowedFormats();

    String getDescription();
}
