package be.degreyt.mmdoc.cardlibrary;

import be.degreyt.mmdoc.datamodel.Card;
import be.degreyt.mmdoc.datamodel.Expansion;

import java.io.InputStream;
import java.util.List;

public interface CardParser {
    List<Card> parse(InputStream stream, Expansion expansion);
}
