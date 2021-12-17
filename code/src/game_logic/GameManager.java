package game_logic;

import update.DrawMap;
import model.Map.Map;
import model.Map.importMap;
import model.Coordinate;
import model.characters.Tower;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import vue.Navigator;

import java.util.ArrayList;


public class GameManager {
    private Map gameMap;                       // The painted map used as the backgrounds layer
    public GameState game;                        // Provides basic game states.
    private  Scene gameScene;                       // The main viewport
    private vue.game gameController;         // Handles fxml attributes (buttons and labels)


    public void initialize() throws java.io.IOException{
        // Initializes the game state
        game = GameState.getNewGame();
        game.setScore(200);

        /**
         * Choix d'utiliser la classe generationMap ou importMap
         */
        gameMap = new importMap(1280 ,800);
        DrawMap dr = new DrawMap(gameMap);

        FXMLLoader loader = new FXMLLoader(Navigator.GAMEUI);
        StackPane gamePane = new StackPane();
        Group tilemapGroup = new Group();
        tilemapGroup.getChildren().add(dr);
        gamePane.getChildren().add(tilemapGroup);

        // Opens stream to get controller reference
        Node gameUI = (Node) loader.load(Navigator.GAMEUI.openStream());
        gamePane.getChildren().add(gameUI);
        gameScene = new Scene(gamePane);
        gameScene.getStylesheets().add(GameManager.class.getResource("/FXML/gamestyle.css").toExternalForm());
        gameController = loader.getController();
        gameController.setGameManager(this);

        Navigator.stage.setScene(gameScene);
    }

    public void initializeConsole() throws java.io.IOException{

        /**
         * Choix d'utiliser la classe generationMap ou importMap
         */
        try {
            gameMap = new importMap(1280, 800);
        }catch (Exception e){
            System.out.println("erreur import map" + e);
        }

        try {
            ArrayList<Coordinate> val = gameMap.getPath();
            for(Coordinate elem: val)
            {
                System.out.println(elem.getTileX() + "  " + elem.getTileY());
            }
        }catch (Exception e){
            System.out.println("erreur getPath pour les monstres map" + e);
        }

        Tower tower1 = new Tower(10,10);


    }

}
