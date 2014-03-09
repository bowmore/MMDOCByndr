package be.degreyt.mmdoc.interfaces.importers;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private List<XCard> cards = new ArrayList<>();

    private List<TemplateEffect> templateEffects = new ArrayList<>();

    public void add(XCard xCard) {
        cards.add(xCard);
    }
}
