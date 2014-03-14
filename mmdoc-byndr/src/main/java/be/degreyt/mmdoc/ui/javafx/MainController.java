package be.degreyt.mmdoc.ui.javafx;

import be.degreyt.mmdoc.byndr.services.ByndrService;
import be.degreyt.mmdoc.byndr.services.CardCollection;
import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.datamodel.CardType;
import be.degreyt.mmdoc.exceptions.UnderlyingIOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.FlowPane;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainController {

    private final ByndrService byndrService;

    @FXML private FlowPane resultPane;

    private final Map<CardOwnership, Node> screens = new HashMap<>();
    private CardCollection cardCollection;

    @Inject
    public MainController(ByndrService byndrService) {
        this.byndrService = byndrService;
    }

    public void initScreen() {
        cardCollection = byndrService.load();
        cardCollection.ownerships().forEach(this::addGlyph);
    }

    private void addGlyph(CardOwnership ownership) {
        try {
            if (screens.containsKey(ownership)) {
                resultPane.getChildren().add(screens.get(ownership));
                return;
            }
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/be/degreyt/mmdoc/ui/CardGlyph.fxml"));
            Parent loadScreen = (Parent) myLoader.load();
            resultPane.getChildren().add(loadScreen);
            GlyphController contentController = ((GlyphController) myLoader.getController());
            contentController.set(ownership);
            screens.put(ownership, loadScreen);
        } catch (IOException e) {
            throw new UnderlyingIOException(e);
        }
    }

    public void heroCardTypeSelectionChanged(ActionEvent actionEvent) {
        resultPane.getChildren().clear();
        if (((CheckBox) actionEvent.getSource()).isSelected()) {
            cardCollection.ownerships(byndrService.getFilterProvider().hasType(CardType.HERO)).forEach(this::addGlyph);
        } else {
            cardCollection.ownerships().forEach(this::addGlyph);
        }
    }
}