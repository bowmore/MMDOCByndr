package be.degreyt.mmdoc.cardprovider.impl;

import be.degreyt.mmdoc.cardprovider.CardProvider;
import be.degreyt.mmdoc.datamodel.*;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by WDH on 09/03/14.
 */
class CardProviderImpl implements CardProvider {
    private List<Hero> heroes;
    private List<Event> events;
    private List<Creature> creatures;
    private List<Spell> spells;
    private List<Fortune> fortunes;
    private List<Building> buildings;

    public CardProviderImpl(List<Hero> heroes, List<Event> events, List<Creature> creatures, List<Spell> spells, List<Fortune> fortunes, List<Building> buildings) {
        this.heroes = heroes;
        this.events = events;
        this.creatures = creatures;
        this.spells = spells;
        this.fortunes = fortunes;
        this.buildings = buildings;
    }

    @Override
    public List<Hero> getHeroes() {
        return heroes;
    }

    @Override
    public List<Event> getEvents() {
        return events;
    }

    @Override
    public List<Creature> getCreatures() {
        return creatures;
    }

    @Override
    public List<Spell> getSpells() {
        return spells;
    }

    @Override
    public List<Fortune> getFortunes() {
        return fortunes;
    }

    @Override
    public List<Building> getBuildings() {
        return buildings;
    }

    @Override
    public List<Card> getCards() {
        return ImmutableList.<Card>builder()
                .addAll(heroes)
                .addAll(events)
                .addAll(creatures)
                .addAll(spells)
                .addAll(fortunes)
                .addAll(buildings)
                .build();
    }
}
