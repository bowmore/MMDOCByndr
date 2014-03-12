package be.degreyt.mmdoc.cardprovider.impl;

import be.degreyt.mmdoc.cardprovider.CardLoader;
import be.degreyt.mmdoc.cardprovider.CardProvider;
import be.degreyt.mmdoc.datamodel.*;
import be.degreyt.mmdoc.datamodel.impl.*;
import be.degreyt.mmdoc.exceptions.MissingExpansionCode;
import be.degreyt.mmdoc.exceptions.UnderlyingUrlException;
import be.degreyt.mmdoc.interfaces.importers.CardsXmlParser;
import be.degreyt.mmdoc.interfaces.importers.data.XCard;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;

import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WDH on 09/03/14.
 */
class CardLoaderImpl implements CardLoader {

    public static final String BASE_RESOURCE_PATH = "file:./mmdoc-byndr/src/main/resources/images/";
    private final Provider<CardBuilder> cardBuilderProvider;
    private final Pattern FILE_NAME_PATTERN = Pattern.compile("cards_(\\w{3})(_\\w)?\\.xml");

    @Inject
    public CardLoaderImpl(Provider<CardBuilder> cardBuilderProvider) {
        this.cardBuilderProvider = cardBuilderProvider;
    }

    @Override
    public CardProvider loadCards() {
        return doLoadCards(new CardsXmlParser());
    }

    public CardProvider loadCards(String parentDirectoryPath) {
        return doLoadCards(new CardsXmlParser(parentDirectoryPath));
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DataModelModule());
        CardProvider cardProvider = injector.getInstance(CardLoaderImpl.class).loadCards();
        System.out.println(cardProvider.getHeroes().size());
        System.out.println(cardProvider.getEvents().size());
        System.out.println(cardProvider.getCreatures().size());
        System.out.println(cardProvider.getSpells().size());
        System.out.println(cardProvider.getFortunes().size());
        System.out.println(cardProvider.getBuildings().size());
    }

    private CardProvider doLoadCards(CardsXmlParser parser) {
        List<Hero> heroes = new ArrayList<>();
        List<Event> events = new ArrayList<>();
        List<Creature> creatures = new ArrayList<>();
        List<Spell> spells = new ArrayList<>();
        List<Fortune> fortunes = new ArrayList<>();
        List<Building> buildings = new ArrayList<>();
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
        return new CardProviderImpl(heroes, events, creatures, spells, fortunes, buildings);
    }

    private Hero parseHero(XCard card) {
        return new HeroImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(), parseMagicSchools(card.getSubType()), parseSmallUrl(card), parseLargeUrl(card), parseExpansionInfos(card));
    }

    private Set<ExpansionInfo> parseExpansionInfos(XCard card) {
        Optional<Expansion> expansion = Expansion.forCode(getExpansionCode(card).orElseThrow(MissingExpansionCode::new));
        if (expansion.isPresent()) {
            ExpansionInfo expansionInfo = new ExpansionInfoImpl(expansion.get(), Rarity.valueOf(card.getRarity().toUpperCase()));
            return Collections.singleton(expansionInfo);
        }
        return Collections.emptySet();
    }

    private URL parseSmallUrl(XCard card) {
        return getUrl(card, "small/");
    }

    private URL parseLargeUrl(XCard card) {
        return getUrl(card, "large/");
    }

    private URL getUrl(XCard card, String sizeFolder) {
        String resourceName = buildResourceName(card);
        if (resourceName == null) {
            return null;
        }
        String urlString = BASE_RESOURCE_PATH + sizeFolder + resourceName;
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new UnderlyingUrlException(e);
        }
    }

    private String buildResourceName(XCard card) {
        StringBuilder fileName = new StringBuilder();
        fileName.append(getExpansionCode(card).orElseThrow(MissingExpansionCode::new))
                .append('_')
                .append(card.getName())
                .append(".jpg");
        return fileName.toString();
    }

    private Optional<String> getExpansionCode(XCard card) {
        Matcher matcher = FILE_NAME_PATTERN.matcher(card.getFileName());
        return matcher.matches() ? Optional.of(matcher.group(1)) : Optional.<String>empty();
    }

    private Event parseEvent(XCard card) {
        return new EventImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(), parseSmallUrl(card), parseLargeUrl(card), parseExpansionInfos(card));
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
                .health(parseLevel(card.getHP()))
                .setSmallImageUrl(parseSmallUrl(card))
                .setLargeImageUrl(parseLargeUrl(card));
        parseExpansionInfos(card).forEach(creatureBuilder::expansionInfo);
        if (parseUnique(card)) {
            creatureBuilder.unique();
        }
        parsePositionTypes(card.getSubType()).stream().forEach(creatureBuilder::position);
        parseCreatureTypes(card.getSubType()).stream().forEach(creatureBuilder::creatureType);
        parseAbilities(card).stream().forEach(creatureBuilder::ability);
        return creatureBuilder.build();
    }

    private Spell parseSpell(XCard card) {
        return new SpellImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(),
                parseLevel(card.getCost()), parseLevel(card.getMightLevel()), parseLevel(card.getMagicLevel()), parseLevel(card.getDestinyLevel()),
                parseUnique(card), parseMagicSchool(card.getSubType()), parsePlayType(card.getSubType()), parseSmallUrl(card), parseLargeUrl(card), parseExpansionInfos(card));
    }

    private Fortune parseFortune(XCard card) {
        return new FortuneImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(),
                parseLevel(card.getCost()), parseLevel(card.getMightLevel()), parseLevel(card.getMagicLevel()), parseLevel(card.getDestinyLevel()),
                parseUnique(card), parsePlayType(card.getSubType()), parseSmallUrl(card), parseLargeUrl(card), parseExpansionInfos(card));
    }

    private Building parseBuilding(XCard card) {
        return new BuildingImpl(parseFaction(card.getFaction()), card.getDisplayName(), card.getDescription(),
                parseLevel(card.getCost()), parseLevel(card.getMightLevel()), parseLevel(card.getMagicLevel()), parseLevel(card.getDestinyLevel()),
                parseUnique(card), parseSmallUrl(card), parseLargeUrl(card), parseExpansionInfos(card));
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
        return "True".equals(card.getUnique());
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
