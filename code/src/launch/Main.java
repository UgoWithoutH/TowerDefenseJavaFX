package launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ScreenController;

import java.io.IOException;

public class Main extends Application {

    /**
     * Dimension de la fenêtre X
     */
    public final static int RESOLUTION_X = 1216;
    /**
     * Dimension de la fenêtre Y
     */
    public final static int RESOLUTION_Y = 672;

    /**
     * Demarrage de la fenêtre JavaFX
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Tower Defense");
        stage.setWidth(RESOLUTION_X);
        stage.setHeight(RESOLUTION_Y);
        stage.setResizable(false);

        new ScreenController(stage);

        /**
         * Premier affichage de fenêtre !!
         */
        try {
            stage.setScene(new Scene(FXMLLoader.load((Main.class.getResource("/fxml/MainMenu.fxml")))));
            stage.getScene().getStylesheets().setAll(Main.class.getResource("/fxml/MenuStyle.css").toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    /**
     * Fermeture de la Fenêtre JavaFX
     */
    @Override
    public void stop(){
        System.out.println("Window is closing");
    }

    /**
     * Fonction de départ de l'application
     * @param args  String[] arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}