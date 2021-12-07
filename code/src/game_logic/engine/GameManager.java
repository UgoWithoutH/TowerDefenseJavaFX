package game_logic.engine;

import game_logic.Map.Map;
import game_logic.engine.characters.Monster;
import game_logic.engine.characters.Projectile;
import game_logic.engine.characters.Tower;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import launch.Navigator;

import java.util.Iterator;


public class GameManager {
    private Map gameMap;                       // The painted map used as the backgrounds layer
    private  Group monsterLayer;                    // Used for the monster graphics
    private  GameState game;                        // Provides basic game states.
    private  Scene gameScene;                       // The main viewport
    private  GameController gameController;         // Handles fxml attributes (buttons and labels)
    private  AnimationTimer gameLoop;               // Used for the gui thread


    public void initialize() throws java.io.IOException{
        // Initializes the game state
        game = GameState.getNewGame();

        // Generates the map with the given resolution
        gameMap = new Map(1280 ,800);

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
