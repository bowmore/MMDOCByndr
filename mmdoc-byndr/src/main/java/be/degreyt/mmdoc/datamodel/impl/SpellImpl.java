package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.Faction;
import be.degreyt.mmdoc.datamodel.MagicSchool;
import be.degreyt.mmdoc.datamodel.PlayType;
import be.degreyt.mmdoc.datamodel.Spell;

/**
 * Created by WDH on 09/03/14.
 */
public class SpellImpl extends AbstractHandCard implements Spell {

    private MagicSchool school;
    private PlayType playType;

    SpellImpl(Faction faction, String name, String description, int cost, int might, int magic, int destiny, boolean unique) {
        super(faction, name, description, cost, might, magic, destiny, unique);
    }

    @Override
    public MagicSchool getMagicSchool() {
        return school;
    }

    @Override
    public PlayType getPlayType() {
        return playType;
    }
}
