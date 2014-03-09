package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.Event;
import be.degreyt.mmdoc.datamodel.Faction;

/**
 * Created by WDH on 09/03/14.
 */
public class EventImpl extends AbstractCard implements Event {
    EventImpl(Faction faction, String name, String description) {
        super(faction, name, description);
    }
}
