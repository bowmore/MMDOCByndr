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

    HeroImpl(Faction faction, String name, String description) {
        super(faction, name, description);
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
}
