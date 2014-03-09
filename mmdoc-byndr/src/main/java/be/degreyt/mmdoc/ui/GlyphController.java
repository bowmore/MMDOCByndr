package be.degreyt.mmdoc.ui;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;


public class GlyphController {

    @FXML ImageView imageView;
    @FXML Label cardName;

    public void set(CardOwnership ownership) {
        cardName.setText(ownership.getCard().getName());
        try (InputStream inputStream = ownership.getCard().smallImageUrl().get().openStream()) {
            Image image = new Image(inputStream);
            imageView.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
