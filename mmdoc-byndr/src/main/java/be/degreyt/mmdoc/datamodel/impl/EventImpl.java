package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.*;

import java.net.URL;
import java.util.Set;

/**
 * Created by WDH on 09/03/14.
 */
public class EventImpl extends AbstractCard implements Event {
    public EventImpl(String identification, Faction faction, String name, Rarity rarity, String description, URL smallImageUrl, URL largeImageUrl, Set<ExpansionInfo> expansionInfos) {
        super(identification, faction, name, rarity, description, expansionInfos);
    }

    @Override
    public CardType getCardType() {
        return CardType.EVENT;
    }

}
