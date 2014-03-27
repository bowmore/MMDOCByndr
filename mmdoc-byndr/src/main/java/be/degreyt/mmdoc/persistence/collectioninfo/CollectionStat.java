package be.degreyt.mmdoc.persistence.collectioninfo;

/**
 * Created by WDH on 27/03/14.
 */
class CollectionStat {
    int owned;
    int wanted;
    boolean alternateArt;
    boolean premium;

    public int getOwned() {
        return owned;
    }

    public void setOwned(int owned) {
        this.owned = owned;
    }

    public int getWanted() {
        return wanted;
    }

    public void setWanted(int wanted) {
        this.wanted = wanted;
    }

    public boolean isAlternateArt() {
        return alternateArt;
    }

    public void setAlternateArt(boolean alternateArt) {
        this.alternateArt = alternateArt;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
