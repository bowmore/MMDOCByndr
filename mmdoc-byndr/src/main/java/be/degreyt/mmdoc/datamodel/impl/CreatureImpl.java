package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;

import java.net.URL;
import java.util.Set;

final class CreatureImpl extends AbstractHandCard implements Creature {

    private final Set<PositionType> positionTypes;
    private final Set<CreatureType> creatureTypes;
    private final Set<Ability> abilities;
    private final int attack;
    private final int retaliation;
    private final int health;

    CreatureImpl(String identification, Faction faction, String name, Rarity rarity, String description,
                 int cost, int might, int magic, int destiny,
                 boolean unique, Set<PositionType> positionTypes, Set<CreatureType> creatureTypes, Set<Ability> abilities,
                 int attack, int retaliation, int health, URL smallImageUrl, Set<ExpansionInfo> expansionInfos, URL largeImageUrl) {
        super(identification, faction, name, rarity, description, cost, might, magic, destiny, unique, smallImageUrl, largeImageUrl, expansionInfos);
        this.positionTypes = positionTypes;
        this.creatureTypes = creatureTypes;
        this.abilities = abilities;
        this.attack = attack;
        this.retaliation = retaliation;
        this.health = health;
    }

    @Override
    public Set<PositionType> getPositionTypes() {
        return positionTypes;
    }

    @Override
    public Set<CreatureType> getCreatureTypes() {
        return creatureTypes;
    }

    @Override
    public Set<Ability> getAbilities() {
        return abilities;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getRetaliation() {
        return retaliation;
    }

    @Override
    public int getHealth() {
        return health;
    }


    @Override
    public CardType getCardType() {
        return CardType.CREATURE;
    }

}
