package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;
import be.degreyt.mmdoc.exceptions.UnderlyingUrlException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

abstract class AbstractCard implements Card {
    private static final String BASE_RESOURCE_PATH = "file:./mmdoc-byndr/src/main/resources/images/";

    private final String identification;
    private final String description;
    private final String name;
    private final Faction faction;
    private final Set<ExpansionInfo> expansionInfos;
    private final Rarity rarity;


    AbstractCard(String identification, Faction faction, String name, Rarity rarity, String description, Set<ExpansionInfo> expansionInfos) {
        this.identification = identification;
        this.faction = faction;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
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
    public URL smallImageUrl() {
        return getUrl(this, "small/");
    }

    @Override
    public URL largeImageUrl() {
        return getUrl(this, "large/");
    }

    @Override
    public String getIdentification() {
        return identification;
    }

    @Override
    public Expansion getExpansion() {
        return expansionInfos.stream().map(ExpansionInfo::getExpansion).findFirst().orElse(null);
    }

    private URL getUrl(Card card, String sizeFolder) {
        String resourceName = buildResourceName(card);
        if (resourceName == null) {
            return null;
        }
        String urlString = BASE_RESOURCE_PATH + sizeFolder + resourceName;
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new UnderlyingUrlException(e);
        }
    }

    private String buildResourceName(Card card) {
        StringBuilder fileName = new StringBuilder();
        fileName.append(card.getExpansion().getExpansionCode())
                .append('_')
                .append(card.getIdentification())
                .append(".jpg");
        return fileName.toString();
    }

}
