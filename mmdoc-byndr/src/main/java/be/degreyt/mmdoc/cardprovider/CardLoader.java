package be.degreyt.mmdoc.cardprovider;

import be.degreyt.mmdoc.datamodel.*;
import be.degreyt.mmdoc.datamodel.impl.*;
import be.degreyt.mmdoc.interfaces.importers.CardsXmlParser;
import be.degreyt.mmdoc.interfaces.importers.data.XCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by WDH on 09/03/14.
 */
public class CardLoader {
    public static CardProvider loadCards() {
        return doLoadCards(new CardsXmlParser());
    }

    public static CardProvider loadCards(String parentDirectoryPath) {
        return doLoadCards(new CardsXmlParser(parentDirectoryPath));
    }

    public static void main(String[] args) {
        CardProvider cardProvider = loadCards();
        System.out.println(cardProvider.getHeroes().size());
        System.out.println(cardProvider.getEvents().size());
        System.out.println(cardProvider.getCreatures().size());
        System.out.println(cardProvider.getSpells().size());
        System.out.println(cardProvider.getFortunes().size());
        System.out.println(cardProvider.getBuildings().size());
    }

    private static CardProvider doLoadCards(CardsXmlParser parser) {
        List<Hero> heroes = new ArrayList<Hero>();
        List<Event> events = new ArrayList<Event>();
        List<Creature> creatures = new ArrayList<Creature>();
        List<Spell> spells = new ArrayList<Spell>();
        List<Fortune> fortunes = new ArrayList<Fortune>();
        List<Building> buildings = new ArrayList<Building>();
        for (XCard card : parser.parse()) {
            switch (card.getType()) {
                case "Hero":
                    heroes.add(parseHero(card));
                    break;
                case "Event":
                    events.add(parseEvent(card));
                    break;
                case "Unit":
                    creatures.add(parseCreature(card));
                    break;
                case "Spell":
                    spells.add(parseSpell(card));
                    break;
                case "Fortune":
                    fortunes.add(parseFortune(card));
                    break;
                case "Building":
                    buildings.add(parseBuilding(card));
                    break;
                default: break;
            }
        }
        return new CardProvider(heroes, events, creatures, spells, fortunes, buildings);
    }

    private static Hero parseHero(XCard card) {
        return new HeroImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription());
    }

    private static Event parseEvent(XCard card) {
        return new EventImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription());
    }

    private static Creature parseCreature(XCard card) {
        return new CreatureImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(),
                parseLevel(card.getCost()), parseLevel(card.getMightLevel()), parseLevel(card.getMagicLevel()), parseLevel(card.getDestinyLevel()),
                parseUnique(card), parsePositionTypes(card.getSubType()), parseCreatureTypes(card.getSubType()), parseAbilities(card),
                parseLevel(card.getAttack()), parseLevel(card.getRetaliate()), parseLevel(card.getHP()));
    }

    private static Spell parseSpell(XCard card) {
        return new SpellImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(),
                parseLevel(card.getCost()), parseLevel(card.getMightLevel()), parseLevel(card.getMagicLevel()), parseLevel(card.getDestinyLevel()),
                parseUnique(card));
    }

    private static Fortune parseFortune(XCard card) {
        return new FortuneImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(),
                parseLevel(card.getCost()), parseLevel(card.getMightLevel()), parseLevel(card.getMagicLevel()), parseLevel(card.getDestinyLevel()),
                parseUnique(card));
    }

    private static Building parseBuilding(XCard card) {
        return new BuildingImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(),
                parseLevel(card.getCost()), parseLevel(card.getMightLevel()), parseLevel(card.getMagicLevel()), parseLevel(card.getDestinyLevel()),
                parseUnique(card));
    }

    private static int parseLevel(String levelString) {
        if (levelString == null || levelString.trim().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(levelString);
        }
    }

//todo implement

    private static Faction parseFaction(String faction) {
        return null;
    }

    private static boolean parseUnique(XCard card) {
        return false;
    }

    private static Set<CreatureType> parseCreatureTypes(String subtype) {
        return Collections.emptySet();
    }

    private static Set<PositionType> parsePositionTypes(String subtype) {
        return Collections.emptySet();
    }

    private static Set<Ability> parseAbilities(XCard card) {
        return Collections.emptySet();
    }
}
