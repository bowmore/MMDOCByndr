package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.Building;
import be.degreyt.mmdoc.datamodel.Faction;

/**
 * Created by WDH on 09/03/14.
 */
public class BuildingImpl extends AbstractHandCard implements Building {
    public BuildingImpl(Faction faction, String name, String description, int cost, int might, int magic, int destiny, boolean unique) {
        super(faction, name, description, cost, might, magic, destiny, unique);
    }
}
