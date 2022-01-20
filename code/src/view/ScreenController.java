package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import launch.Main;
import model.Manager;
import model.ScoreRanking;

import java.io.IOException;
import java.util.HashMap;

/**
 * Gère les vues
 */
public class ScreenController {

    private static HashMap<String, Pane> screenMap = new HashMap<>();
    private static Stage stage;
    private static Manager manager;

    public ScreenController(Stage stage) {
        this.stage = stage;
        this.manager = new Manager(new ScoreRanking());
        addMenuView();
    }

    public static Stage getStage(){return stage;}
    public static void setStage(Stage stage1){stage = stage1;}

    public static Manager getManager() {return manager;}
    public static void setManager(Manager manager) {ScreenController.manager = manager;}

    /**
     * Fonction à ajouter une fois que toutes les propriétés de la page sont correctement chargées
     * @param name
     * @param pane
     */
    public static void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    /**
     * Supprimer une vue
     * @param name
     */
    private void removeScreen(String name){
        screenMap.remove(name);
    }

    /**
     * Activer une vue
     * @param name
     */
    public static void activate(String name){
        stage.getScene().setRoot( screenMap.get(name) );
    }


    /**
     * Ajoute une Vue Menu dans le HashMap pour faciliter le changement de Vue
     */
    private void addMenuView(){
        try
        {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/MainMenu.fxml"));
            Pane root = loader.load();
            ScreenController.addScreen("setup", root);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
