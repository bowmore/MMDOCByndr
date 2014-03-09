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
    public CardBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public CardBuilder faction(Faction faction) {
        this.faction = faction;
        return this;
    }

    @Override
    public CardBuilder cost(int cost) {
        this.cost = cost;
        return this;
    }

    @Override
    public CardBuilder might(int might) {
        this.might = might;
        return this;
    }

    @Override
    public CardBuilder magic(int magic) {
        this.magic = magic;
        return this;
    }

    @Override
    public CardBuilder destiny(int destiny) {
        this.destiny = destiny;
        return this;
    }

    @Override
    public CardBuilder unique() {
        this.unique = true;
        return this;
    }

    @Override
    public CreatureBuilder attack(int attack) {
        this.attack = attack;
        return new InternalCreatureBuilder();
    }

    @Override
    public CreatureBuilder health(int health) {
        this.health = health;
        return new InternalCreatureBuilder();
    }

    @Override
    public CreatureBuilder retaliation(int retaliation) {
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
        public CreatureBuilder name(String name) {
            CardBuilderImpl.this.name(name);
            return this;
        }

        @Override
        public CreatureBuilder faction(Faction faction) {
            CardBuilderImpl.this.faction(faction);
            return this;
        }

        @Override
        public CreatureBuilder cost(int cost) {
            CardBuilderImpl.this.cost(cost);
            return this;
        }

        @Override
        public CreatureBuilder might(int might) {
            CardBuilderImpl.this.might(might);
            return this;
        }

        @Override
        public CreatureBuilder magic(int magic) {
            CardBuilderImpl.this.magic(magic);
            return this;
        }

        @Override
        public CreatureBuilder destiny(int destiny) {
            CardBuilderImpl.this.destiny(destiny);
            return this;
        }

        @Override
        public CreatureBuilder unique() {
            CardBuilderImpl.this.unique();
            return this;
        }

        @Override
        public CreatureBuilder attack(int attack) {
            CardBuilderImpl.this.attack = attack;
            return this;
        }

        @Override
        public CreatureBuilder health(int health) {
            CardBuilderImpl.this.health = health;
            return this;
        }

        @Override
        public CreatureBuilder retaliation(int retaliation) {
            CardBuilderImpl.this.retaliation = retaliation;
            return this;
        }

        @Override
        public CreatureBuilder position(PositionType positionType) {
            positionTypes.add(positionType);
            return this;
        }

        @Override
        public CreatureBuilder creatureType(CreatureType creatureType) {
            creatureTypes.add(creatureType);
            return this;
        }

        @Override
        public CreatureBuilder ability(Ability ability) {
            abilities.add(ability);
            return this;
        }

        @Override
        public Creature build() {
            return new CreatureImpl(faction, name, description, cost, might, magic, destiny, unique, positionTypes.build(), creatureTypes.build(), abilities.build(), attack, retaliation, health);
        }
    }
}
