package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;
import com.google.common.collect.ImmutableSet;

class CardBuilderImpl implements CardBuilder {

    private String description = "";
    private String name = null;
    private Faction faction = null;

    // HandCard
    private int cost;
    private int might;
    private int magic;
    private int destiny;
    private boolean unique;

    // Creature
    private ImmutableSet.Builder<PositionType> positionTypes = new ImmutableSet.Builder<>();
    private ImmutableSet.Builder<CreatureType> creatureTypes = new ImmutableSet.Builder<>();
    private ImmutableSet.Builder<Ability> abilities = new ImmutableSet.Builder<>();
    private int attack;
    private int retaliation;
    private int health;



    @Override
    public CardBuilder description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public CardBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public CardBuilder setFaction(Faction faction) {
        this.faction = faction;
        return this;
    }

    @Override
    public CardBuilder setCost(int cost) {
        this.cost = cost;
        return this;
    }

    @Override
    public CardBuilder setMight(int might) {
        this.might = might;
        return this;
    }

    @Override
    public CardBuilder setMagic(int magic) {
        this.magic = magic;
        return this;
    }

    @Override
    public CardBuilder setDestiny(int destiny) {
        this.destiny = destiny;
        return this;
    }

    @Override
    public CardBuilder unique() {
        this.unique = true;
        return this;
    }

    @Override
    public CreatureBuilder setAttack(int attack) {
        this.attack = attack;
        return new InternalCreatureBuilder();
    }

    @Override
    public CreatureBuilder setHealth(int health) {
        this.health = health;
        return new InternalCreatureBuilder();
    }

    @Override
    public CreatureBuilder setRetaliation(int retaliation) {
        this.retaliation = retaliation;
        return new InternalCreatureBuilder();
    }

    private class InternalCreatureBuilder implements CreatureBuilder {
        @Override
        public CreatureBuilder description(String description) {
            CardBuilderImpl.this.description(description);
            return this;
        }

        @Override
        public CreatureBuilder setName(String name) {
            CardBuilderImpl.this.setName(name);
            return this;
        }

        @Override
        public CreatureBuilder setFaction(Faction faction) {
            CardBuilderImpl.this.setFaction(faction);
            return this;
        }

        @Override
        public CreatureBuilder setCost(int cost) {
            CardBuilderImpl.this.setCost(cost);
            return this;
        }

        @Override
        public CreatureBuilder setMight(int might) {
            CardBuilderImpl.this.setMight(might);
            return this;
        }

        @Override
        public CreatureBuilder setMagic(int magic) {
            CardBuilderImpl.this.setMagic(magic);
            return this;
        }

        @Override
        public CreatureBuilder setDestiny(int destiny) {
            CardBuilderImpl.this.setDestiny(destiny);
            return this;
        }

        @Override
        public CreatureBuilder unique() {
            CardBuilderImpl.this.unique();
            return this;
        }

        @Override
        public CreatureBuilder setAttack(int attack) {
            CardBuilderImpl.this.attack = attack;
            return this;
        }

        @Override
        public CreatureBuilder setHealth(int health) {
            CardBuilderImpl.this.health = health;
            return this;
        }

        @Override
        public CreatureBuilder setRetaliation(int retaliation) {
            CardBuilderImpl.this.retaliation = retaliation;
            return this;
        }

        @Override
        public Creature build() {
            return new CreatureImpl(faction, name, description, cost, might, magic, destiny, unique, positionTypes.build(), creatureTypes.build(), abilities.build(), attack, retaliation, health);
        }
    }
}
