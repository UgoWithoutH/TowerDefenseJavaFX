package game_logic;

import Vue.GameController;
import game_logic.Map.Map;
import game_logic.Map.importMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import Vue.Navigator;


public class GameManager {
    private Map gameMap;                       // The painted map used as the backgrounds layer
    private  GameState game;                        // Provides basic game states.
    private  Scene gameScene;                       // The main viewport
    private GameController gameController;         // Handles fxml attributes (buttons and labels)


    public void initialize() throws java.io.IOException{
        // Initializes the game state
        game = GameState.getNewGame();

        /**
         * Choix d'utiliser la classe generationMap ou importMap
         */
        gameMap = new importMap(1280 ,800);

        // Creates gui hierarchy
        FXMLLoader loader = new FXMLLoader(Navigator.GAMEUI);
        StackPane gamePane = new StackPane();
        Group tilemapGroup = new Group();
        tilemapGroup.getChildren().add(gameMap);
        gamePane.getChildren().add(tilemapGroup);

        // Opens stream to get controller reference
        Node gameUI = (Node)loader.load(Navigator.GAMEUI.openStream());
        gamePane.getChildren().add(gameUI);
        gameScene = new Scene(gamePane);
        gameScene.getStylesheets().add(GameManager.class.getResource("/FXML/gamestyle.css").toExternalForm());
        gameController = loader.<GameController>getController();
        gameController.setGameManager(this);

        Navigator.stage.setScene(gameScene);
    }

}
