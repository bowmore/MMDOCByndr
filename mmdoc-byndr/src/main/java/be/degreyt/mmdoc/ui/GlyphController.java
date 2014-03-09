package be.degreyt.mmdoc.ui;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class GlyphController {

    @FXML ImageView imageView;
    @FXML Label cardName;

    public void set(CardOwnership ownership) {
        cardName.setText(ownership.getCard().getName());
    }
}
