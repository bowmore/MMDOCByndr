package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;
import com.google.common.collect.ImmutableSet;

import java.net.URL;

class CardBuilderImpl implements CardBuilder {

    private String identification = "";
    private String description = "";
    private String name = null;
    private Faction faction = null;

    private URL smallImageUrl;
    private URL largeImageUrl;
    private final ImmutableSet.Builder<ExpansionInfo> expansionInfos = ImmutableSet.builder();


    // HandCard
    private int cost;
    private int might;
    private int magic;
    private int destiny;
    private boolean unique;

    // Creature
    private final ImmutableSet.Builder<PositionType> positionTypes = ImmutableSet.builder();
    private final ImmutableSet.Builder<CreatureType> creatureTypes = ImmutableSet.builder();
    private final ImmutableSet.Builder<Ability> abilities = ImmutableSet.builder();
    private int attack;
    private int retaliation;
    private int health;
    private CardType cardType;
    private Rarity rarity;

    private final ImmutableSet.Builder<MagicSchool> schools = ImmutableSet.builder();

    @Override
    public CardBuilder identification(String identification) {
        this.identification = identification;
        return this;
    }

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

    @Override
    public CardBuilder rarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public CardBuilder setSmallImageUrl(URL smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
        return this;
    }

    public CardBuilder setLargeImageUrl(URL largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
        return this;
    }

    public CardBuilder expansionInfo(ExpansionInfo expansionInfo) {
        expansionInfos.add(expansionInfo);
        return this;
    }

    @Override
    public CardBuilder type(CardType cardType) {
        this.cardType = cardType;
        return this;
    }

    @Override
    public Card build() {
        switch (cardType) {
            case HERO:
                return new HeroImpl(identification, faction, name, rarity, description, schools.build(), smallImageUrl, largeImageUrl, expansionInfos.build(), might, magic, destiny);
        }
        return null;
    }

    private class InternalCreatureBuilder implements CreatureBuilder {
        @Override
        public CreatureBuilder identification(String identification) {
            CardBuilderImpl.this.identification(identification);
            return this;
        }

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
            return new CreatureImpl(identification, faction, name, rarity, description, cost, might, magic, destiny, unique, positionTypes.build(), creatureTypes.build(), abilities.build(), attack, retaliation, health, smallImageUrl, expansionInfos.build(), largeImageUrl);
        }

        @Override
        public CreatureBuilder rarity(Rarity rarity) {
            CardBuilderImpl.this.rarity = rarity;
            return this;
        }

        @Override
        public CreatureBuilder setSmallImageUrl(URL smallImageUrl) {
            CardBuilderImpl.this.smallImageUrl = smallImageUrl;
            return this;
        }

        @Override
        public CreatureBuilder setLargeImageUrl(URL largeImageUrl) {
            CardBuilderImpl.this.largeImageUrl = largeImageUrl;
            return this;
        }

        @Override
        public CreatureBuilder expansionInfo(ExpansionInfo expansionInfo) {
            CardBuilderImpl.this.expansionInfos.add(expansionInfo);
            return this;
        }

        @Override
        public CardBuilder type(CardType cardType) {
            CardBuilderImpl.this.cardType = cardType;
            return this;
        }

    }
}
