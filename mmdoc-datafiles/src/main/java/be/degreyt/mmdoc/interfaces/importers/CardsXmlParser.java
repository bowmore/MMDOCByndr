package be.degreyt.mmdoc.interfaces.importers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CardsXmlParser {

    private XStream xStream;

    public static void main(String[] args) {
        try {
            XStream xStream = new CardsXmlParser().getXStream();

            Cards c = new Cards();
            XCard xCard = new XCard();
            xCard.setRarity("Common");
            c.add(xCard);
            System.out.println(xStream.toXML(c));

            Cards cards;
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\src\\main\\resources\\gamedata\\cards_b01.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_b01_1.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_b01_2.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_b01_3.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_rew.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s01.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s01_1.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s01_2.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s01_3.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s02.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s02_1.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s02_2.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s02_3.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s03.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s03_1.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s03_2.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s03_3.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s04.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s04_1.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s04_2.xml")));
            cards = (Cards) xStream.fromXML(new FileInputStream(new File(".\\mmdoc-datafiles\\\\src\\main\\resources\\gamedata\\cards_s04_3.xml")));
            System.out.println(cards);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private XStream getXStream() {
        if (xStream == null) {
            initXStream();
        }
        return xStream;
    }

    private void initXStream() {
        xStream = new XStream(new StaxDriver());
        xStream.alias("Cards", Cards.class);
        xStream.addImplicitCollection(Cards.class, "cards", XCard.class);
        xStream.addImplicitCollection(Cards.class, "templateEffects", TemplateEffect.class);
        xStream.addImplicitCollection(XCard.class, "schools");
        xStream.addImplicitCollection(XCard.class, "variables");
        xStream.addImplicitCollection(Subgroup.class, "targets");
        xStream.addImplicitCollection(Effect.class, "abilities");
        xStream.alias("Card", XCard.class);
        xStream.alias("TemplateEffect", TemplateEffect.class);
        xStream.alias("School", School.class);
        xStream.alias("Ability", XAbility.class);
        xStream.alias("Target", Target.class);
        xStream.addImplicitCollection(Target.class, "variables");
        xStream.alias("Trigger", Trigger.class);
        xStream.alias("Variable", Variable.class);
        xStream.alias("Variable2", Variable.class);
        xStream.alias("Variable3", Variable.class);
        xStream.alias("Variable4", Variable.class);
        xStream.alias("Variable5", Variable.class);
        xStream.alias("Variable6", Variable.class);
        xStream.alias("Variable7", Variable.class);

        xStream.addImplicitCollection(Trigger.class, "targets");
        xStream.alias("Effect", Effect.class);
        xStream.alias("Condition", Condition.class);
        xStream.addImplicitCollection(Condition.class, "conditions");
        xStream.useAttributeFor(XCard.class, "Rarity");
        xStream.useAttributeFor(XCard.class, "Type");
        xStream.useAttributeFor(XCard.class, "SubType");
        xStream.useAttributeFor(XCard.class, "DisplayName");
        xStream.useAttributeFor(XCard.class, "Name");
        xStream.useAttributeFor(XCard.class, "Faction");
        xStream.useAttributeFor(XCard.class, "Cost");
        xStream.useAttributeFor(XCard.class, "DestinyLevel");
        xStream.useAttributeFor(XCard.class, "MagicLevel");
        xStream.useAttributeFor(XCard.class, "MightLevel");
        xStream.useAttributeFor(XCard.class, "Attack");
        xStream.useAttributeFor(XCard.class, "Retaliate");
        xStream.useAttributeFor(XCard.class, "HP");
    }

    public Cards parse(InputStream inputStream) {
        return (Cards) getXStream().fromXML(inputStream);
    }
}
