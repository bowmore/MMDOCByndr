package be.degreyt.mmdoc.cardprovider;

import be.degreyt.mmdoc.datamodel.*;

import java.util.List;

/**
 * Created by WDH on 09/03/14.
 */
public class CardProvider {
    private List<Hero> heroes;
    private List<Event> events;
    private List<Creature> creatures;
    private List<Spell> spells;
    private List<Fortune> fortunes;
    private List<Building> buildings;

    public List<Hero> getHeroes() {
        return heroes;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public List<Fortune> getFortunes() {
        return fortunes;
    }

    public List<Building> getBuildings() {
        return buildings;
    }
}
