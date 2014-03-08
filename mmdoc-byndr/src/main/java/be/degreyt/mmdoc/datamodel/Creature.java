package be.degreyt.mmdoc.datamodel;

import java.util.Set;

/**
 * Created by WDH on 26/02/14.
 */
public interface Creature extends HandCard {
    Set<PositionType> getPositionTypes();
    Set<CreatureType> getCreatureTypes();
    Set<Ability> getAbilities();
    int getAttack();
    int getRetaliation();
    int getHealth();
}
