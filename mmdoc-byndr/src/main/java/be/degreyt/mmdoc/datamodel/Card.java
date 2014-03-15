package be.degreyt.mmdoc.datamodel;

import be.degreyt.mmdoc.utils.ComparisonBuilder;
import com.google.common.base.Optional;

import java.net.URL;
import java.util.Set;

public interface Card extends Comparable<Card> {
    String getIdentification();
    String getName();
    Faction getFaction();
    Set<ExpansionInfo> getExpansionInfo();
    int getWildCardCost();
    Set<Format> getAllowedFormats();
    CardType getCardType();

    String getDescription();

    Optional<URL> smallImageUrl();
    Optional<URL> largeImageUrl();

    default int compareTo(Card other) {
        return new ComparisonBuilder()
                .add(getCardType(), other.getCardType())
                .add(getFaction(), other.getFaction())
                .add(getIdentification(), other.getIdentification())
                .compare();
    }
}
