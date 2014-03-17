package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;

import java.net.URL;
import java.util.Set;

/**
 * Created by WDH on 09/03/14.
 */
public class HeroImpl extends AbstractCard implements Hero {

    private final int might;
    private final int magic;
    private final int destiny;
    private Set<HeroAbility> heroAbilities;
    private Set<MagicSchool> magicSchools;

    public HeroImpl(String identification, Faction faction, String name, Rarity rarity, String description, Set<MagicSchool> magicSchools, URL smallImageUrl, URL largeImageUrl, Set<ExpansionInfo> expansionInfos, int might, int magic, int destiny) {
        super(identification, faction, name, rarity, description, smallImageUrl, largeImageUrl, expansionInfos);
        this.magicSchools = magicSchools;
        this.might = might;
        this.magic = magic;
        this.destiny = destiny;
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

    @Override
    public CardType getCardType() {
        return CardType.HERO;
    }
}
