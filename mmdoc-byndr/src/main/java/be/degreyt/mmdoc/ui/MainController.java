package be.degreyt.mmdoc.ui;

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

import java.util.HashMap;
import java.util.Map;

public class MainController {

    @FXML private StackPane stackPane;

    private final Map<String, Node> screens = new HashMap<>();

    public MainController() {
        loadScreen("collection", "/be/degreyt/mmdoc/ui/Collection.fxml");
        loadScreen("wishList", "/be/degreyt/mmdoc/ui/WishList.fxml");
        loadScreen("wildcards", "/be/degreyt/mmdoc/ui/Wildcards.fxml");
    }

    void showInitialScreen() {
        setScreen("collection");
    }

    public void showCollection() {
        System.out.println("Collection selected");
        setScreen("collection");
    }

    public void showWishList() {
        System.out.println("WishList selected");
        setScreen("wishList");
    }

    public void showWildcards() {
        System.out.println("Wildcards selected");
        setScreen("wildcards");
    }

    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            screens.put(name, loadScreen);
            ContentController contentController = ((ContentController) myLoader.getController());
            contentController.setScreenParent(stackPane);
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void setScreen(final String name) {

        if(screens.get(name) != null) { //screen loaded 
            final DoubleProperty opacity = opacityProperty();

            //Is there is more than one screen 
            if(!getChildren().isEmpty()){
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(opacity,1.0)),
                        new KeyFrame(new Duration(100),

                                new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent t) {
                                        //remove displayed screen 
                                        getChildren().remove(0);
                                        //add new screen 
                                        getChildren().add(0, screens.get(name));
                                        Timeline fadeIn = new Timeline(
                                                new KeyFrame(Duration.ZERO,
                                                        new KeyValue(opacity, 0.0)),
                                                new KeyFrame(new Duration(800),
                                                        new KeyValue(opacity, 1.0)));
                                        fadeIn.play();
                                    }
                                }, new KeyValue(opacity, 0.0)));
                fade.play();
            } else {
                //no one else been displayed, then just show 
                setOpacity(0.0);
                getChildren().add(screens.get(name));
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500),
                                new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
        } else {
            throw new RuntimeException("Screen not loaded " + name); // TODO proper exceptions
        }
    }

    private void setOpacity(double value) {
        stackPane.setOpacity(value);
    }

    private DoubleProperty opacityProperty() {
        return stackPane.opacityProperty();
    }

    private ObservableList<javafx.scene.Node> getChildren() {
        return stackPane.getChildren();
    }
}