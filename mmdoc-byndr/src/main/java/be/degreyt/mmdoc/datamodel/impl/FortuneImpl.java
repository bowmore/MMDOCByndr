package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.Faction;
import be.degreyt.mmdoc.datamodel.Fortune;
import be.degreyt.mmdoc.datamodel.PlayType;

/**
 * Created by WDH on 09/03/14.
 */
public class FortuneImpl extends AbstractHandCard implements Fortune {

    private PlayType playType;

    public FortuneImpl(Faction faction, String name, String description, int cost, int might, int magic, int destiny, boolean unique, PlayType playType) {
        super(faction, name, description, cost, might, magic, destiny, unique);
        this.playType = playType;
    }

    @Override
    public PlayType getPlayType() {
        return playType;
    }
}
