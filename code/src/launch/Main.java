package launch;

import vue.main_vue;
import vue.main_menu;
import vue.Navigator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public final static int RESOLUTION_X = 1216;
    public final static int RESOLUTION_Y = 672;

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Tower Defense");
        stage.setScene(
                createScene(
                        loadMainPane(stage)
                )
        );

        stage.setWidth(RESOLUTION_X);
        stage.setHeight(RESOLUTION_Y);
        stage.setResizable(false);
        stage.show();
        Scene scene = new Scene(loadMainPane(stage));
        stage.setScene(scene);
        Navigator.setStage(stage);
        //main_menu start = new main_menu();
        //start.startNewGame();

    }

    /**
     * Charge le Holder (vue Principale)
     * et premiere vue de l'application
     */
    private Pane loadMainPane(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        "/FXML/main_vue.fxml"
                )
        );

        main_vue mainController = loader.getController();

        Navigator.setMainController(mainController);
        Navigator.setStage(stage);
        Navigator.loadVista("/FXML/main_menu.fxml");

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
        /*
        main_menu start = new main_menu();
        start.startNewGameConsole();
        */
    }
}