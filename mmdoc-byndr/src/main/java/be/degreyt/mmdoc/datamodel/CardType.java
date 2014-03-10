package be.degreyt.mmdoc.datamodel;

/**
 * Created by WDH on 10/03/14.
 */
public enum CardType {
    HERO(Hero.class), EVENT(Event.class), CREATURE(Creature.class), SPELL(Spell.class), FORTUNE(Fortune.class), BUILDING(Building.class);

    CardType(Class<? extends Card> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Card> getClazz() {
        return clazz;
    }

    public static CardType forClass(Class<? extends Card> clazz) {
        for (CardType cardType : values()) {
            if (cardType.getClazz().equals(clazz)) {
                return cardType;
            }
        }
        return null;
    }

    private final Class<? extends Card> clazz;
}
