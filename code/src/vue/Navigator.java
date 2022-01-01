package vue;

//import game.engine.GameManager;
import game_logic.GameManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Utilitaire pour échanger de vue
 *
 */
public class Navigator {


    public static final URL GAMEUI = GameManager.class.getResource("/FXML/game.fxml");

    /** stage for game*/
    private static Stage stage;

    public static Stage getStage(){return stage;}

    public static void setStage(Stage stage1){
        stage = stage1;
    }

    /**
     * Charge la vue spécifié par le holder.FXML lors du
     * lancement de l'application (defaut)
     *
     * Chaque chargement de vue ne sont pas mis en cache,
     * cela cree forcement de nouvelle vue a chaque fois
     *
     *
     * a améliorer:
     *   cache FXMLLoaders
     *   charger ou creer une vue
     *   permettre de revenir en arriére ou en avant
     *
     * @param fxml fxml a charger
     */
    public static void loadVista(String fxml) {
        try {
            stage.setScene(new Scene(FXMLLoader.load((Navigator.class.getResource(fxml)))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mainMenu(){
        Navigator.loadVista("/FXML/main_menu.fxml");
        stage.getScene().getStylesheets().setAll(
                Navigator.class.getResource("/FXML/menustyle.css").toExternalForm());
    }

}