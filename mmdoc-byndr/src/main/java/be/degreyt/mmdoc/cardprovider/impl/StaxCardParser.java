package be.degreyt.mmdoc.cardprovider.impl;

import be.degreyt.mmdoc.cardprovider.CardParser;
import be.degreyt.mmdoc.datamodel.*;
import com.google.inject.Provider;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

import javax.inject.Inject;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class StaxCardParser implements CardParser {

    @Inject
    public StaxCardParser(Provider<CardBuilder> cardBuilderProvider) {
        this.cardBuilderProvider = cardBuilderProvider;
    }


    private final Provider<CardBuilder> cardBuilderProvider;

    @Override
    public List<Card> parse(InputStream stream, Expansion expansion) {
        List<Card> cards = new ArrayList<>();
        try {

            XMLInputFactory2 xmlInputFactory = (XMLInputFactory2) XMLInputFactory2.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.FALSE);
            xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
            xmlInputFactory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.FALSE);
            xmlInputFactory.configureForSpeed();

            CardBuilder builder = null;

            XMLStreamReader2 reader = (XMLStreamReader2) xmlInputFactory.createXMLStreamReader(stream);
            boolean inDescription = false;

            for (int event = reader.next(); event != XMLEvent.END_DOCUMENT; event = reader.next()) {
                if (event == XMLEvent.START_ELEMENT) {

                    if (atCard(reader)) {
                        builder = cardBuilderProvider.get();
                        builder.expansion(expansion);
                    }

                    for (int i = 0; i < reader.getAttributeCount(); i++) {
                        String value = reader.getAttributeValue(i);
                        if (atCard(reader)) {
                            switch (reader.getAttributeName(i).getLocalPart()) {
                                case "Rarity":
                                    builder = builder.rarity(Rarity.valueOf(value.toUpperCase()));
                                    break;
                                case "Type":
                                    if (value.equalsIgnoreCase("unit")) {
                                        value = CardType.CREATURE.name();
                                    }
                                    builder = builder.type(CardType.valueOf(value.toUpperCase()));
                                    break;
                                case "Name":
                                    builder = builder.identification(value);
                                    break;
                                case "DisplayName":
                                    builder = builder.name(value);
                                    break;
                                case "Faction":
                                    builder = builder.faction(Faction.valueOf(value.toUpperCase()));
                                    break;
                                case "MightLevel":
                                    builder = builder.might(Integer.valueOf(value));
                                    break;
                                case "MagicLevel":
                                    builder = builder.magic(Integer.valueOf(value));
                                    break;
                                case "DestinyLevel":
                                    builder = builder.destiny(Integer.valueOf(value));
                                    break;
                                case "HP":
                                    builder = builder.health(Integer.valueOf(value));
                                    break;
                                case "Attack":
                                    builder = builder.attack(Integer.valueOf(value));
                                    break;
                                case "Retaliate":
                                    builder = builder.retaliation(Integer.valueOf(value));
                                    break;
                                case "Cost":
                                    builder = builder.cost(Integer.valueOf(value));
                                    break;
                                case "Unique":
                                    if ("true".equalsIgnoreCase(value)) {
                                        builder = builder.unique();
                                    }
                                    break;
                                case "SubType":
                                    String[] subtypes = value.split("\\|");
                                    for (String subtype : subtypes) {
                                        if ("Magic".equalsIgnoreCase(subtype)) {
                                            builder = builder.creatureType(CreatureType.MAGIC);
                                        } else {
                                            builder = builder.position(PositionType.valueOf(subtype.toUpperCase()));
                                        }
                                    }
                                    break;
                                case "School":
                                    if (value.equalsIgnoreCase("prime")) {
                                        value = MagicSchool.PRIMAL.name();
                                    }
                                    builder = builder.addSchool(MagicSchool.valueOf(value.toUpperCase()));
                                    break;
                                default:
                            }
                        }
                        if (atSchool(reader) && inCard(builder)) {
                            switch (reader.getAttributeName(i).getLocalPart()) {
                                case "Name":
                                    if (value.equalsIgnoreCase("prime")) {
                                        value = MagicSchool.PRIMAL.name();
                                    }
                                    builder = builder.addSchool(MagicSchool.valueOf(value.toUpperCase()));
                                default:
                            }
                        }
                        if (atTag(reader, "Ongoing") && inCard(builder)) {
                            builder = builder.playType(PlayType.ONGOING);
                        }
                    }
                    if (atDescription(reader) && inCard(builder)) {
                        inDescription = true;
                    }
                }
                if (event == XMLEvent.END_ELEMENT) {
                    if (atCard(reader)) {
                        cards.add(builder.build());
                        builder = null;
                    }
                    if (atDescription(reader) && inCard(builder)) {
                        inDescription = false;
                    }
                }
                if (event == XMLEvent.CHARACTERS) {
                    if (inDescription && inCard(builder)) {
                        builder = builder.description(reader.getText());
                    }
                }
            }

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return cards;
    }

    private boolean atDescription(XMLStreamReader2 reader) {
        return atTag(reader, "Description");
    }

    private boolean inCard(CardBuilder builder) {
        return builder != null;
    }

    private boolean atSchool(XMLStreamReader2 reader) {
        return atTag(reader, "School");
    }

    private boolean atCard(XMLStreamReader2 reader) {
        return atTag(reader, "Card");
    }

    private boolean atTag(XMLStreamReader2 reader, String tag) {
        return tag.equals(reader.getName().getLocalPart());
    }
}
