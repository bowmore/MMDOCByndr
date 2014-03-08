package be.degreyt.mmdoc.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CollectionController implements ContentController, Initializable {

    @FXML private BorderPane root;

    private StackPane parent;

    @Override
    public void setScreenParent(StackPane stackPane) {
        parent = stackPane;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
