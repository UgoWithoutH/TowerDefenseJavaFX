package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import launch.Main;
import model.Manager;
import model.ScoreRanking;

import java.io.IOException;
import java.util.HashMap;

public class ScreenController {

    private static HashMap<String, Pane> screenMap = new HashMap<>();

    private static Stage stage;
    private static Manager manager;

    public ScreenController(Stage stage) {
        this.stage = stage;
        this.manager = new Manager(new ScoreRanking());
        addChaffinchMenu();
    }

    public static Stage getStage(){return stage;}
    public static void setStage(Stage stage1){stage = stage1;}

    public static Manager getManager() {return manager;}
    public static void setManager(Manager manager) {ScreenController.manager = manager;}

    /**
     * Fonction a ajouté une fois que toutes les propriétés de la page sont correctement chargé
     * @param name
     * @param pane
     */
    public static void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    public static void activate(String name){
        stage.getScene().setRoot( screenMap.get(name) );
    }

    private static void addChaffinchMenu(){
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
