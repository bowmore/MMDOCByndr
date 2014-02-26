package be.degreyt.mmdoc.datamodel;

/**
 * Created by WDH on 26/02/14.
 */
public interface HandCard extends Card, Describable {
    int getCost();
    int getMightRequirement();
    int getMagicRequirement();
    int getDestinyRequirement();
    boolean isUnique();
}
