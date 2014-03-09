package be.degreyt.mmdoc.datamodel;

import com.google.common.base.Optional;

import java.net.URL;
import java.util.Set;

public interface Card {
    String getName();
    Faction getFaction();
    Set<ExpansionInfo> getExpansionInfo();
    int getWildCardCost();
    Set<Format> getAllowedFormats();

    String getDescription();

    Optional<URL> smallImageUrl();
    Optional<URL> largeImageUrl();
}
