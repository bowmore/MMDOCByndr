package be.degreyt.mmdoc.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class ByndrApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/be/degreyt/mmdoc/ui/Main.fxml"));
            Parent page = (Parent) myLoader.load();
            URL resource = ByndrApplication.class.getResource("/be/degreyt/mmdoc/ui/Main.fxml");
            MainController mainController = myLoader.getController();
            Scene scene = new Scene(page);
            scene.getStylesheets().add("/be/degreyt/mmdoc/ui/Byndr.css");
            primaryStage.setScene(scene);
            primaryStage.setTitle("MMDOCByndr");
            mainController.showInitialScreen();
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}
