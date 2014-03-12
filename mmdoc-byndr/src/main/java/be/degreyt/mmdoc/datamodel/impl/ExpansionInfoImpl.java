package be.degreyt.mmdoc.datamodel.impl;

import be.degreyt.mmdoc.datamodel.Expansion;
import be.degreyt.mmdoc.datamodel.ExpansionInfo;
import be.degreyt.mmdoc.datamodel.Rarity;

public class ExpansionInfoImpl implements ExpansionInfo {
    private final Expansion expansion;

    public ExpansionInfoImpl(Expansion expansion, Rarity rarity) {
        this.expansion = expansion;
        this.rarity = rarity;
    }

    private final Rarity rarity;

    @Override
    public Expansion getExpansion() {
        return expansion;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }
}
