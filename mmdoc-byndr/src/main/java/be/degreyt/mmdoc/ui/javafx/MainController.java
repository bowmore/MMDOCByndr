package be.degreyt.mmdoc.ui.javafx;

import be.degreyt.mmdoc.byndr.services.ByndrService;
import be.degreyt.mmdoc.byndr.services.CardCollection;
import be.degreyt.mmdoc.byndr.services.CardOwnership;
import be.degreyt.mmdoc.exceptions.UnderlyingIOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainController {

    private final ByndrService byndrService;

    @FXML private FlowPane resultPane;

    private final Map<String, Node> screens = new HashMap<>();

    @Inject
    public MainController(ByndrService byndrService) {
        this.byndrService = byndrService;
    }

    public void initScreen() {
        CardCollection cardCollection = byndrService.load();
        Set<CardOwnership> ownerships = cardCollection.ownerships();
        for (CardOwnership ownership : ownerships) {
            try {
                FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/be/degreyt/mmdoc/ui/CardGlyph.fxml"));
                Parent loadScreen = (Parent) myLoader.load();
                resultPane.getChildren().add(loadScreen);
                GlyphController contentController = ((GlyphController) myLoader.getController());
                contentController.set(ownership);
            } catch (IOException e) {
                throw new UnderlyingIOException(e);
            }
        }
    }

}