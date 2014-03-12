package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.Building;
import be.degreyt.mmdoc.datamodel.ExpansionInfo;
import be.degreyt.mmdoc.datamodel.Faction;

import java.net.URL;
import java.util.Set;

/**
 * Created by WDH on 09/03/14.
 */
public class BuildingImpl extends AbstractHandCard implements Building {
    public BuildingImpl(Faction faction, String name, String description, int cost, int might, int magic, int destiny, boolean unique, URL smallImageUrl, URL largeImageUrl, Set<ExpansionInfo> expansionInfos) {
        super(faction, name, description, cost, might, magic, destiny, unique, smallImageUrl, largeImageUrl, expansionInfos);
    }
}
