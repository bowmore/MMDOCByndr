package be.degreyt.mmdoc.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class ByndrApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            URL resource = ByndrApplication.class.getResource("/be/degreyt/mmdoc/ui/Main.fxml");
            BorderPane page = FXMLLoader.load(resource);
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("MMDOCByndr");
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}
