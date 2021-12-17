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
    private  Scene gameScene;
    private vue.game gameController;
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
        gameMap = new importMap(1280 ,800);

        /**
         * Attention pour dessiner une tower, et pour pouvoir l'utiliser aprés, il faut surtout creer l'objet TOWER
         * et le mettre dans le arrayList<Tower>
         */
        Tower tr1 = new Tower(7,5);
        gameMap.setMapNode(tr1.getTileX(), tr1.getTileY(), 7);
        playerTowers.add(tr1);
        DrawMap dr = new DrawMap(gameMap);





        /** todo
         *   Tout ce gros tas a déplacer dans un endroit approprié ( je ne sais pas ou mais je pense que c'est nécessaire)
         *   Cela n'a aucun rapport avec le management de la partie (maybe dans la vue main_menu)
         *
         */
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
