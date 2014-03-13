package be.degreyt.mmdoc.datamodel;

/**
 * Created by WDH on 26/02/14.
 */
public interface Spell extends HandCard, HasMagicSchools {
    MagicSchool getMagicSchool();
    PlayType getPlayType();
}
