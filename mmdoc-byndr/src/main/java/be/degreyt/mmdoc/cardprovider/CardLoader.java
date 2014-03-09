package be.degreyt.mmdoc.cardprovider;

import be.degreyt.mmdoc.byndr.services.impl.ByndrServicesModule;
import be.degreyt.mmdoc.datamodel.*;
import be.degreyt.mmdoc.datamodel.impl.*;
import be.degreyt.mmdoc.interfaces.importers.CardsXmlParser;
import be.degreyt.mmdoc.interfaces.importers.data.XCard;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by WDH on 09/03/14.
 */
public class CardLoader {

    private final Provider<CardBuilder> cardBuilderProvider;

    @Inject
    public CardLoader(Provider<CardBuilder> cardBuilderProvider) {
        this.cardBuilderProvider = cardBuilderProvider;
    }

    public CardProvider loadCards() {
        return doLoadCards(new CardsXmlParser());
    }

    public CardProvider loadCards(String parentDirectoryPath) {
        return doLoadCards(new CardsXmlParser(parentDirectoryPath));
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DataModelModule());
        CardProvider cardProvider = injector.getInstance(CardLoader.class).loadCards();
        System.out.println(cardProvider.getHeroes().size());
        System.out.println(cardProvider.getEvents().size());
        System.out.println(cardProvider.getCreatures().size());
        System.out.println(cardProvider.getSpells().size());
        System.out.println(cardProvider.getFortunes().size());
        System.out.println(cardProvider.getBuildings().size());
    }

    private CardProvider doLoadCards(CardsXmlParser parser) {
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
                default:
                    break;
            }
        }
        return new CardProvider(heroes, events, creatures, spells, fortunes, buildings);
    }

    private Hero parseHero(XCard card) {
        return new HeroImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(), parseMagicSchools(card.getSubType()));
    }

    private Event parseEvent(XCard card) {
        return new EventImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription());
    }

    private Creature parseCreature(XCard card) {
        CreatureBuilder creatureBuilder = cardBuilderProvider.get()
                .faction(parseFaction(card.getFaction()))
                .name(card.getDisplayName())
                .description(card.getDescription())
                .cost(parseLevel(card.getCost()))
                .might(parseLevel(card.getMightLevel()))
                .magic(parseLevel(card.getMagicLevel()))
                .destiny(parseLevel(card.getDestinyLevel()))
                .attack(parseLevel(card.getAttack()))
                .retaliation(parseLevel(card.getRetaliate()))
                .health(parseLevel(card.getHP()));
        if (parseUnique(card)) {
            creatureBuilder.unique();
        }
        for (PositionType positionType : parsePositionTypes(card.getSubType())) {
            creatureBuilder.position(positionType);
        }
        for (CreatureType creatureType : parseCreatureTypes(card.getSubType())) {
            creatureBuilder.creatureType(creatureType);
        }
        for (Ability ability : parseAbilities(card)) {
            creatureBuilder.ability(ability);
        }
        return creatureBuilder.build();
    }

    private Spell parseSpell(XCard card) {
        return new SpellImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(),
                parseLevel(card.getCost()), parseLevel(card.getMightLevel()), parseLevel(card.getMagicLevel()), parseLevel(card.getDestinyLevel()),
                parseUnique(card), parseMagicSchool(card.getSubType()), parsePlayType(card.getSubType()));
    }

    private Fortune parseFortune(XCard card) {
        return new FortuneImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(),
                parseLevel(card.getCost()), parseLevel(card.getMightLevel()), parseLevel(card.getMagicLevel()), parseLevel(card.getDestinyLevel()),
                parseUnique(card), parsePlayType(card.getSubType()));
    }

    private Building parseBuilding(XCard card) {
        return new BuildingImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(),
                parseLevel(card.getCost()), parseLevel(card.getMightLevel()), parseLevel(card.getMagicLevel()), parseLevel(card.getDestinyLevel()),
                parseUnique(card));
    }

    private int parseLevel(String levelString) {
        if (levelString == null || levelString.trim().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(levelString);
        }
    }

//todo implement

    private Faction parseFaction(String faction) {
        return null;
    }

    private boolean parseUnique(XCard card) {
        return false;
    }

    private Set<CreatureType> parseCreatureTypes(String subtype) {
        return Collections.emptySet();
    }

    private Set<PositionType> parsePositionTypes(String subtype) {
        return Collections.emptySet();
    }

    private Set<Ability> parseAbilities(XCard card) {
        return Collections.emptySet();
    }

    private Set<MagicSchool> parseMagicSchools(String subType) {
        return Collections.emptySet();
    }

    private MagicSchool parseMagicSchool(String subType) {
        return null;
    }

    private PlayType parsePlayType(String subType) {
        return null;
    }
}
