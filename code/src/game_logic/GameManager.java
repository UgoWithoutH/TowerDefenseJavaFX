package game_logic;

import boucleJeu.Boucle;
import boucleJeu.Observateur;
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


public class GameManager implements Observateur {

    private Map gameMap;
    private GameState game;
    private GameViewLogic gameViewLogic;

    private int lives;
    private int resources;
    private int level;
    private ArrayList<Tower> playerTowers;
    private ArrayList<Monster> monstersAlive;
    private Boucle boucle;

    public GameManager(){
        resources = 10000;
        level = 0;
        lives = 20;
        playerTowers = new ArrayList<Tower>();
        monstersAlive = new ArrayList<Monster>();
        boucle = new Boucle();
        boucle.subscribe(this);
    }

    public GameState getGame(){
        return game;
    }

    public void initialize(GameViewLogic gameViewLogic, Map map) throws java.io.IOException{
        this.gameViewLogic = gameViewLogic;
        this.gameMap = map;
        game = GameState.getNewGame();
        game.setScore(200);
        Monster.setPath(gameMap.getPath());
        boucle.start();
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

    @Override
    public void update(int timer) {
        if(true) {
            createMonster(3);
        }
        else if(timer <= 0){
            game.setLevel(game.getLevel() + 1);
            timer = 30;
        }
    }

    private void createMonster(int health){
        game.getMonstersAlive().add(new Monster(health));
        gameViewLogic.createMonster(health);
    }

    private synchronized void removeMonster(Monster monster){
        if (monster.isPathFinished()){
            game.setLives((game.getLives()) - 1);
        }
        else{
            game.setResources((game.getResources()) + monster.getReward());
            game.setScore(game.getScore() + (monster.getReward() * game.getLevel()));
        }
        monster.getView().setVisible(false);
        game.getMonstersAlive().remove(monster);

    }
}
