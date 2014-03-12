package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;

import java.net.URL;
import java.util.Set;

/**
 * Created by WDH on 09/03/14.
 */
public class SpellImpl extends AbstractHandCard implements Spell {

    private MagicSchool school;
    private PlayType playType;

    public SpellImpl(Faction faction, String name, String description, int cost, int might, int magic, int destiny, boolean unique, MagicSchool school, PlayType playType, URL smallImageUrl, URL largeImageUrl, Set<ExpansionInfo> expansionInfos) {
        super(faction, name, description, cost, might, magic, destiny, unique, smallImageUrl, largeImageUrl, expansionInfos);
        this.school = school;
        this.playType = playType;
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
