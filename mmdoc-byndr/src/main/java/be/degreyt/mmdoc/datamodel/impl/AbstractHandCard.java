package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.Faction;
import be.degreyt.mmdoc.datamodel.HandCard;

abstract class AbstractHandCard extends AbstractCard implements HandCard {
    private final int cost;
    private final int might;
    private final int magic;
    private final int destiny;
    private final boolean unique;

    AbstractHandCard(Faction faction, String name, String description, int cost, int might, int magic, int destiny, boolean unique) {
        super(faction, name, description);
        this.cost = cost;
        this.might = might;
        this.magic = magic;
        this.destiny = destiny;
        this.unique = unique;
    }

    public int getCost() {
        return cost;
    }

    public int getMightRequirement() {
        return might;
    }

    public int getMagicRequirement() {
        return magic;
    }

    public int getDestinyRequirement() {
        return destiny;
    }

    public boolean isUnique() {
        return unique;
    }

}
