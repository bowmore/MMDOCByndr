package be.degreyt.byndr.datamodel;

import java.util.Set;

/**
 * Created by WDH on 26/02/14.
 */
public interface Hero extends Card {
    int getStartingMight();
    int getStartingMagic();
    int getStartingDestiny();
    Set<HeroAbility> getHeroAbilities();

}
