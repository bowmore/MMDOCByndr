package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;
import com.google.common.base.Optional;

import java.net.URL;
import java.util.Set;

abstract class AbstractCard implements Card {
    private final String identification;
    private final String description;
    private final String name;
    private final Faction faction;
    private final URL smallImageUrl;
    private final URL largeImageUrl;
    private final Set<ExpansionInfo> expansionInfos;
    private final Rarity rarity;


    AbstractCard(String identification, Faction faction, String name, Rarity rarity, String description, URL smallImageUrl, URL largeImageUrl, Set<ExpansionInfo> expansionInfos) {
        this.identification = identification;
        this.faction = faction;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.smallImageUrl = smallImageUrl;
        this.largeImageUrl = largeImageUrl;
        this.expansionInfos = expansionInfos;
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
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public final String getDescription() {
        return description;
    }

    @Override
    public Optional<URL> smallImageUrl() {
        return Optional.fromNullable(smallImageUrl);
    }

    @Override
    public Optional<URL> largeImageUrl() {
        return Optional.fromNullable(largeImageUrl);
    }

    @Override
    public String getIdentification() {
        return identification;
    }
}
