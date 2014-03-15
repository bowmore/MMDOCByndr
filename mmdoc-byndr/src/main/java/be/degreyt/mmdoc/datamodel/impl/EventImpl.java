package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.CardType;
import be.degreyt.mmdoc.datamodel.Event;
import be.degreyt.mmdoc.datamodel.ExpansionInfo;
import be.degreyt.mmdoc.datamodel.Faction;

import java.net.URL;
import java.util.Set;

/**
 * Created by WDH on 09/03/14.
 */
public class EventImpl extends AbstractCard implements Event {
    public EventImpl(String identification, Faction faction, String name, String description, URL smallImageUrl, URL largeImageUrl, Set<ExpansionInfo> expansionInfos) {
        super(identification, faction, name, description, smallImageUrl, largeImageUrl, expansionInfos);
    }

    @Override
    public CardType getCardType() {
        return CardType.EVENT;
    }

}
