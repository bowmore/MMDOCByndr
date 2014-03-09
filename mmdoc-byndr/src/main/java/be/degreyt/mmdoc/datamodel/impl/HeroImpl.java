package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;

import java.util.Set;

/**
 * Created by WDH on 09/03/14.
 */
public class HeroImpl extends AbstractCard implements Hero {

    private int might;
    private int magic;
    private int destiny;
    private Set<HeroAbility> heroAbilities;
    private Set<MagicSchool> magicSchools;

    public HeroImpl(Faction faction, String name, String description, Set<MagicSchool> magicSchools) {
        super(faction, name, description);
        this.magicSchools = magicSchools;
    }

    @Override
    public int getStartingMight() {
        return might;
    }

    @Override
    public int getStartingMagic() {
        return magic;
    }

    @Override
    public int getStartingDestiny() {
        return destiny;
    }

    @Override
    public Set<HeroAbility> getHeroAbilities() {
        return heroAbilities;
    }

    @Override
    public Set<MagicSchool> getMagicSchools() {
        return magicSchools;
    }
}
