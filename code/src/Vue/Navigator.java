package Vue;

//import game.engine.GameManager;
import game_logic.GameManager;
import javafx.fxml.FXMLLoader;
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
    public static Stage stage;

    public static void setStage(Stage stage1){
        stage = stage1;
    }
    /** main layout controller. */
    private static main_vue mainController;

    /**
     * enregistre le Controller Main
     *
     * @param mainController Main controller
     */
    public static void setMainController(main_vue mainController) {
        Navigator.mainController = mainController;
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
            mainController.setVista(FXMLLoader.load((Navigator.class.getResource(fxml))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}