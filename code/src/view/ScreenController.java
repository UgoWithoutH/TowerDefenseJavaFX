package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Manager;

import java.util.HashMap;
import java.util.Map;

public class ScreenController {
    /**
     * todo
     *  faire un (autoload)
     */
        private static HashMap<String, Pane> screenMap = new HashMap<>();

        private static Stage stage;
        private static Manager manager;

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

            /*
            for (Map.Entry t : screenMap.entrySet() ){
                System.out.println(t.getValue());
            }
            System.out.println("----------------");
        */
        }

}
