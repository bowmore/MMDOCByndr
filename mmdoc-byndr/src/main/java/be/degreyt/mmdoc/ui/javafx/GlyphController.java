package be.degreyt.mmdoc.ui.javafx;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import com.google.common.base.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class GlyphController {

    @FXML
    ImageView imageView;
    @FXML
    Label cardName;

    public void set(CardOwnership ownership) {
        cardName.setText(ownership.getCard().getName());
        URL smallImageUrl = ownership.getCard().smallImageUrl();
        try (InputStream inputStream = smallImageUrl.openStream()) {
            Image image = new Image(inputStream);
            imageView.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
