package be.degreyt.mmdoc.cardprovider;

import be.degreyt.mmdoc.datamodel.*;

import java.util.List;

public interface CardProvider {
    List<Card> getCards();

    List<Hero> getHeroes();

    List<Event> getEvents();

    List<Creature> getCreatures();

    List<Spell> getSpells();

    List<Fortune> getFortunes();

    List<Building> getBuildings();
}
