package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.Card;
import be.degreyt.mmdoc.datamodel.ExpansionInfo;
import be.degreyt.mmdoc.datamodel.Faction;
import be.degreyt.mmdoc.datamodel.Format;

import java.util.Set;

abstract class AbstractCard implements Card {
    private final String description;
    private final String name;
    private final Faction faction;

    AbstractCard(Faction faction, String name, String description) {
        this.faction = faction;
        this.name = name;
        this.description = description;
    }

    public final String getName() {
        return name;
    }

    public final Faction getFaction() {
        return faction;
    }

    public final Set<ExpansionInfo> getExpansionInfo() {
        return null;
    }

    public final int getWildCardCost() {
        return 0; // TODO get from expansion costs
    }

    public final Set<Format> getAllowedFormats() {
        return null;
    }

    @Override
    public final String getDescription() {
        return description;
    }
}
