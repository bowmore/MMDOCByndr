package be.degreyt.mmdoc.datamodel;

import java.util.Arrays;
import java.util.Optional;

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

    public static Optional<CardType> forClass(Class<? extends Card> clazz) {
        return Arrays.stream(values()).filter((type -> type.getClazz().isAssignableFrom(clazz))).findAny();
    }

    private final Class<? extends Card> clazz;
}
