package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.Event;
import be.degreyt.mmdoc.datamodel.ExpansionInfo;
import be.degreyt.mmdoc.datamodel.Faction;

import java.net.URL;
import java.util.Set;

/**
 * Created by WDH on 09/03/14.
 */
public class EventImpl extends AbstractCard implements Event {
    public EventImpl(Faction faction, String name, String description, URL smallImageUrl, URL largeImageUrl, Set<ExpansionInfo> expansionInfos) {
        super(faction, name, description, smallImageUrl, largeImageUrl, expansionInfos);
    }
}
