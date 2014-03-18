package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;
import be.degreyt.mmdoc.utils.ComparisonBuilder;

import java.net.URL;
import java.util.Collections;
import java.util.Set;

/**
 * Created by WDH on 09/03/14.
 */
public class SpellImpl extends AbstractHandCard implements Spell {

    private MagicSchool school;
    private PlayType playType;

    public SpellImpl(String identification, Faction faction, String name, Rarity rarity, String description, int cost, int might, int magic, int destiny, boolean unique, MagicSchool school, PlayType playType, URL smallImageUrl, URL largeImageUrl, Set<ExpansionInfo> expansionInfos) {
        super(identification, faction, name, rarity, description, cost, might, magic, destiny, unique, smallImageUrl, largeImageUrl, expansionInfos);
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

    @Override
    public Set<MagicSchool> getMagicSchools() {
        return Collections.singleton(getMagicSchool());
    }

    @Override
    public CardType getCardType() {
        return CardType.SPELL;
    }

    @Override
    public int compareTo(Card other) {
        ComparisonBuilder builder = new ComparisonBuilder()
                .add(getCardType(), other.getCardType());
        if (other instanceof Spell) {
            Spell otherSpell = (Spell) other;
            builder.add(getCost(), otherSpell.getCost())
                    .add(getExpansion(), otherSpell.getExpansion())
                    .add(getMagicSchool(), otherSpell.getMagicSchool());
        }
        return builder
                .add(getFaction(), other.getFaction())
                .add(getIdentification(), other.getIdentification())
                .compare();
    }

}
