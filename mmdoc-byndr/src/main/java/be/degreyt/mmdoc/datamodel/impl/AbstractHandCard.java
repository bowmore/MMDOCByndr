package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.ExpansionInfo;
import be.degreyt.mmdoc.datamodel.Faction;
import be.degreyt.mmdoc.datamodel.HandCard;

import java.net.URL;
import java.util.Set;

abstract class AbstractHandCard extends AbstractCard implements HandCard {
    private final int cost;
    private final int might;
    private final int magic;
    private final int destiny;
    private final boolean unique;

    AbstractHandCard(Faction faction, String name, String description, int cost, int might, int magic, int destiny, boolean unique, URL smallImageUrl, URL largeImageUrl, Set<ExpansionInfo> expansionInfos) {
        super(faction, name, description, smallImageUrl, largeImageUrl, expansionInfos);
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
