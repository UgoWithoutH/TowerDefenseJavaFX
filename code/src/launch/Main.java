package launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public final static int RESOLUTION_X = 1280;
    public final static int RESOLUTION_Y = 800;

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Tower Defense");
        stage.setScene(
                createScene(
                        loadMainPane()
                )
        );

        stage.setWidth(RESOLUTION_X);
        stage.setHeight(RESOLUTION_Y);
        stage.setResizable(false);
        stage.show();
        Navigator.setStage(stage);
    }

    /**
     * Charge le Holder (vue Principale)
     * et premiere vue de l'application
     */
    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        "/FXML/holder.fxml"
                )
        );

        MainController mainController = loader.getController();

        Navigator.setMainController(mainController);
        Navigator.loadVista("/FXML/mainmenu.fxml");

        return mainPane;
    }

    /**
     * Creer une scnene et implement le css
     *
     * @param mainPane mainPane
     */
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
                mainPane
        );

        scene.getStylesheets().setAll(
                getClass().getResource("/FXML/menustyle.css").toExternalForm()
        );

        return scene;
    }


    public static void main(String[] args) {
        launch(args);

    }
}