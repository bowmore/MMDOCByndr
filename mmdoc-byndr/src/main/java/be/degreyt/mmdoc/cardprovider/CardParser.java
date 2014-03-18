package be.degreyt.mmdoc.cardprovider;

import be.degreyt.mmdoc.datamodel.Card;
import be.degreyt.mmdoc.datamodel.Expansion;
import be.degreyt.mmdoc.datamodel.ExpansionInfo;

import java.io.InputStream;
import java.util.List;

public interface CardParser {
    List<Card> parse(InputStream stream, Expansion expansion);
}
