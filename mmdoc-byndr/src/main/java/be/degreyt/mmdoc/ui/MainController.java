package be.degreyt.mmdoc.ui;

import be.degreyt.mmdoc.byndr.services.ByndrService;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class MainController {

    private final ByndrService byndrService;

    @FXML private StackPane stackPane;

    private final Map<String, Node> screens = new HashMap<>();

    @Inject
    public MainController(ByndrService byndrService) {
        this.byndrService = byndrService;
    }



//    public boolean loadScreen(String name, String resource) {
//        try {
//            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
//            Parent loadScreen = (Parent) myLoader.load();
//            screens.put(name, loadScreen);
//            ContentController contentController = ((ContentController) myLoader.getController());
//            contentController.setScreenParent(stackPane);
//            return true;
//        }catch(Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
}