package be.degreyt.mmdoc.ui.javafx;

import be.degreyt.mmdoc.byndr.services.impl.ByndrServicesModule;
import be.degreyt.mmdoc.cardlibrary.impl.CardProviderModule;
import be.degreyt.mmdoc.datamodel.impl.DataModelModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ByndrApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Injector injector = Guice.createInjector(new ByndrServicesModule(),
                new CardProviderModule(),
                new DataModelModule(),
                new CardProviderModule());
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/be/degreyt/mmdoc/ui/MMDOCByndr.fxml"));
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
            ex.printStackTrace(); // TODO proper handling
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
