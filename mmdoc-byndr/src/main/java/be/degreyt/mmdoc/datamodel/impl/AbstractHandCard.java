package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;
import be.degreyt.mmdoc.utils.ComparisonBuilder;

import java.net.URL;
import java.util.Set;

abstract class AbstractHandCard extends AbstractCard implements HandCard {
    private final int cost;
    private final int might;
    private final int magic;
    private final int destiny;
    private final boolean unique;

    AbstractHandCard(String identification, Faction faction, String name, Rarity rarity, String description, int cost, int might, int magic, int destiny, boolean unique, URL smallImageUrl, URL largeImageUrl, Set<ExpansionInfo> expansionInfos) {
        super(identification, faction, name, rarity, description, expansionInfos);
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

    @Override
    public int compareTo(Card other) {
        ComparisonBuilder builder = new ComparisonBuilder()
                .add(getCardType(), other.getCardType());
        if (other instanceof HandCard) {
            builder.add(getCost(), ((HandCard) other).getCost());
        }
        return builder
                .add(getFaction(), other.getFaction())
                .add(getIdentification(), other.getIdentification())
                .compare();
    }
}