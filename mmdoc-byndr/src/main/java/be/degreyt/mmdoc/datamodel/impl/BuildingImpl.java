package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;

import java.net.URL;
import java.util.Set;

/**
 * Created by WDH on 09/03/14.
 */
public class BuildingImpl extends AbstractHandCard implements Building {
    public BuildingImpl(String identification, Faction faction, String name, Rarity rarity, String description, int cost, int might, int magic, int destiny, boolean unique, URL smallImageUrl, URL largeImageUrl, Set<ExpansionInfo> expansionInfos) {
        super(identification, faction, name, rarity, description, cost, might, magic, destiny, unique, smallImageUrl, largeImageUrl, expansionInfos);
    }

    @Override
    public CardType getCardType() {
        return CardType.BUILDING;
    }
}
