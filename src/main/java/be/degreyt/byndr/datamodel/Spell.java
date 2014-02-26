package be.degreyt.byndr.datamodel;

/**
 * Created by WDH on 26/02/14.
 */
public interface Spell extends HandCard {
    MagicSchool getMagicSchool();
    PlayType getPlayType();
}
