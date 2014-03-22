package be.degreyt.mmdoc.ui.javafx;

import be.degreyt.mmdoc.byndr.services.CardOwnership;
import com.google.common.base.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class GlyphController {

    @FXML
    private ImageView imageView;
    @FXML
    private Label cardName;
    @FXML
    private Label ownedLabel;
    @FXML
    private Label wantedLabel;

    private CardOwnership ownership;

    public void set(CardOwnership ownership) {
        this.ownership = ownership;
        cardName.setText(ownership.getCard().getName());
        URL smallImageUrl = ownership.getCard().smallImageUrl();
        ownedLabel.setText(String.valueOf(ownership.ownedCopies()));
        wantedLabel.setText('/' + String.valueOf(ownership.wantedCopies()));
        try (InputStream inputStream = smallImageUrl.openStream()) {
            Image image = new Image(inputStream);
            imageView.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mouseClickedOnOwned(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            ownership.addOwned(1);
        } else {
            ownership.addOwned(-1);
        }
        ownedLabel.setText(String.valueOf(ownership.ownedCopies()));
    }

    public void mouseClickedOnWanted(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            ownership.addWanted(1);
        } else {
            ownership.addWanted(-1);
        }
        wantedLabel.setText('/' + String.valueOf(ownership.wantedCopies()));
    }
}
