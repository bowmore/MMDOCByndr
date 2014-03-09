package be.degreyt.mmdoc.ui;

import be.degreyt.mmdoc.byndr.services.impl.ByndrServicesModule;
import be.degreyt.mmdoc.datamodel.impl.DataModelModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
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
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/be/degreyt/mmdoc/ui/MMDOCByndr.fxml"));
            Injector injector = Guice.createInjector(new DataModelModule(), new MockByndrServicesModule());
            myLoader.setControllerFactory(new GuiceControllerFactory(injector));
            Parent page = (Parent) myLoader.load();
            MainController mainController = myLoader.getController();
            Scene scene = new Scene(page);
            scene.getStylesheets().add("/be/degreyt/mmdoc/ui/Byndr.css");
            primaryStage.setScene(scene);
            primaryStage.setTitle("MMDOCByndr");
            primaryStage.show();
            mainController.initScreen();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}
