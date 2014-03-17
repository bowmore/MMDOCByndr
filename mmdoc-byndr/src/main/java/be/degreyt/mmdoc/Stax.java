package be.degreyt.mmdoc;

import be.degreyt.mmdoc.byndr.services.ByndrService;
import be.degreyt.mmdoc.byndr.services.impl.ByndrServicesModule;
import be.degreyt.mmdoc.datamodel.*;
import be.degreyt.mmdoc.datamodel.impl.DataModelModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Stax {

    public Stax(Provider<CardBuilder> cardBuilderProvider) {
        this.cardBuilderProvider = cardBuilderProvider;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DataModelModule());
        File file = new File("G:\\Projects\\IdeaProjects\\MMDOCByndr\\mmdoc-datafiles\\src\\main\\resources\\gamedata\\cards_b01.xml");
        try (FileInputStream stream = new FileInputStream(file)) {
            List<Card> read = new Stax(injector.getProvider(CardBuilder.class)).read(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final Provider<CardBuilder> cardBuilderProvider;

    public List<Card> read(InputStream stream) {
        List<Card> cards = new ArrayList<>();
        try {

            XMLInputFactory2 xmlInputFactory = (XMLInputFactory2) XMLInputFactory2.newInstance();
            xmlInputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.FALSE);
            xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
            xmlInputFactory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.FALSE);
            xmlInputFactory.configureForSpeed();

            CardBuilder builder = null;

            XMLStreamReader2 reader = (XMLStreamReader2) xmlInputFactory.createXMLStreamReader(stream);

            for (int event = reader.next(); event != XMLEvent.END_DOCUMENT; event = reader.next()) {
                System.out.print(event + " ");
                if (event == XMLEvent.START_ELEMENT) {
                    System.out.print(reader.getName().toString() + " ");

                    if (atCard(reader)) {
                        builder = cardBuilderProvider.get();
                    }

                    for (int i = 0; i < reader.getAttributeCount(); i++) {
                        String value = reader.getAttributeValue(i);
                        System.out.print(reader.getAttributeName(i) + " = " + value + " : ");
                        if (atCard(reader)) {
                            switch (reader.getAttributeName(i).getLocalPart()) {
                                case "Rarity":
                                    builder.rarity(Rarity.valueOf(value.toUpperCase()));
                                    break;
                                case "Type":
                                    builder.type(CardType.valueOf(value.toUpperCase()));
                                    break;
                                case "Name":
                                    builder.identification(value);
                                    break;
                                case "DisplayName":
                                    builder.name(value);
                                    break;
                                case "Faction":
                                    builder.faction(Faction.valueOf(value.toUpperCase()));
                                    break;
                                case "MightLevel":
                                    builder.might(Integer.valueOf(value));
                                    break;
                                case "MagicLevel":
                                    builder.magic(Integer.valueOf(value));
                                    break;
                                case "DestinyLevel":
                                    builder.destiny(Integer.valueOf(value));
                                    break;
                                case "HP":
                                    builder.health(Integer.valueOf(value));
                                    break;
                                default:
                            }
                        }
                    }
                }
                if (event == XMLEvent.END_ELEMENT) {
                    System.out.print("/" + reader.getName().toString());
                    if (atCard(reader)) {
                        cards.add(builder.build());
                        builder = null;
                    }
                }
                if (event == XMLEvent.CHARACTERS) {
                    System.out.print(" CHARACTERS : " + reader.getText());
                }
                System.out.println();
            }

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return cards;
    }

    private boolean atCard(XMLStreamReader2 reader) {
        return "Card".equals(reader.getName().getLocalPart());
    }
}
