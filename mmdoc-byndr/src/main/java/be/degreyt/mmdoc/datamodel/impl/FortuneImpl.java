package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;

import java.net.URL;
import java.util.Set;

/**
 * Created by WDH on 09/03/14.
 */
public class FortuneImpl extends AbstractHandCard implements Fortune {

    private PlayType playType;

    public FortuneImpl(String identification, Faction faction, String name, String description, int cost, int might, int magic, int destiny, boolean unique, PlayType playType, URL smallImageUrl, URL largeImageUrl, Set<ExpansionInfo> expansionInfos) {
        super(identification, faction, name, description, cost, might, magic, destiny, unique, smallImageUrl, largeImageUrl, expansionInfos);
        this.playType = playType;
    }

    @Override
    public PlayType getPlayType() {
        return playType;
    }

    @Override
    public CardType getCardType() {
        return CardType.FORTUNE;
    }

}
