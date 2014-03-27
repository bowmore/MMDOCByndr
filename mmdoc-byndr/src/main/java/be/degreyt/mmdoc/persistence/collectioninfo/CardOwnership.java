package be.degreyt.mmdoc.persistence.collectioninfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WDH on 27/03/14.
 */
class CardOwnership {
    String cardReference;
    List<CollectionStat> collectionStats = new ArrayList<>();

    public String getCardReference() {
        return cardReference;
    }

    public void setCardReference(String cardReference) {
        this.cardReference = cardReference;
    }

    public List<CollectionStat> getCollectionStats() {
        return collectionStats;
    }

    public void addCollectionStat(CollectionStat collectionStat) {
        collectionStats.add(collectionStat);
    }

    public void addCollectionStats(List<CollectionStat> collectionStats) {
        this.collectionStats.addAll(collectionStats);
    }
}
