package game_logic;

import model.characters.Monster;
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

    private Map gameMap;
    public GameState game;
    private int lives;
    private int resources;
    private int level;
    private ArrayList<Tower> playerTowers;
    private ArrayList<Monster> monstersAlive;

    public GameManager(){
        resources = 10000;
        level = 0;
        lives = 20;
        playerTowers = new ArrayList<Tower>();
        monstersAlive = new ArrayList<Monster>();
    }

    public void initialize() throws java.io.IOException{
        // Initializes the game state
        game = GameState.getNewGame();
        game.setScore(200);

        /**
         * Choix d'utiliser la classe generationMap ou importMap
         */


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

    public Map getGameMap() {
        return gameMap;
    }

    public void setGameMap(Map gameMap) {
        this.gameMap = gameMap;
    }

}
