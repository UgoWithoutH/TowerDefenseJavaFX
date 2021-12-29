package game_logic;

import boucleJeu.Boucle;
import boucleJeu.Observateur;
import javafx.application.Platform;
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
import java.util.Iterator;


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
    private DrawMap drawMap;

    public GameManager(){
        resources = 10000;
        level = 0;
        lives = 20;
        playerTowers = new ArrayList<Tower>();
        monstersAlive = new ArrayList<Monster>();
        boucle = new Boucle(this);
        boucle.subscribe(this);
    }

    public GameViewLogic getGameViewLogic() {
        return gameViewLogic;
    }

    public Boucle getBoucle(){ return boucle; }

    public DrawMap getDrawMap() {
        return drawMap;
    }

    public void setDrawMap(DrawMap drawMap) {
        this.drawMap = drawMap;
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
    }

    public void start(){
        Thread t = new Thread(boucle::start);
        t.start();
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
        try {
            if(timer/5 >= 1 && timer%40 == 0) {
                createMonster(3);
            }
            else if(timer <= 0){
                game.setLevel(game.getLevel() + 1);
            }
            updateLocations();
            attacker();
            gameViewLogic.createProjectiles();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createMonster(int health){
        game.getMonstersAlive().add(new Monster(health));
        gameViewLogic.createMonster(health);
    }

    public synchronized void removeMonster(Monster monster){
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

    private void updateLocations(){
        if(!game.getMonstersAlive().isEmpty()){
            Iterator<Monster> monsters = game.getMonstersAlive().iterator();
            Monster monster;
            while(monsters.hasNext()) {
                monster = monsters.next();
                monster.updateLocation(1);
                if(monster.isPathFinished()){
                    removeMonster(monster);
                }
            }
        }
    }

    public void buyTower(double xCords , double yCords){
        int xTile = (int)(xCords / 64);
        int yTile = (int)(yCords / 64);

        if(gameMap.nodeOpen(xTile,yTile)){
            if(game.getResources() > 49) {
                game.addTower(new Tower(xTile, yTile));
                game.setResources(game.getResources() - 50);
                gameMap.setMapNode(((int) (xCords / 64)), ((int) (yCords / 64)), 7);
                drawMap.draw(gameMap);
            }
        }
    }

    public void attacker() throws InterruptedException {
        Monster target;
        AttackService attackService;
        for (Tower tower : game.getPlayerTowers()) {
            if (tower.isAttaker()) {
                int towerMinXRange = tower.getX() - tower.getAttackRange();
                int towerMaxXRange = tower.getX() + tower.getAttackRange();
                int towerMinYRange = tower.getY() - tower.getAttackRange();
                int towerMaxYRange = tower.getY() + tower.getAttackRange();
                Iterator<Monster> iterator = GameState.getGame().getMonstersAlive().iterator();

                while (iterator.hasNext()) {
                    target = iterator.next();
                    if (target.getX() > towerMinXRange
                            & target.getX() < towerMaxXRange
                            & target.getY() > towerMinYRange
                            & target.getY() < towerMaxYRange) {
                        tower.createProjectile(target);
                        attackService = new AttackService(target, tower);
                        Thread t = new Thread(attackService::run);
                        t.start();
                        target.takeDamage(tower.getAttackDamage());
                        break;
                    }
                }
            }
        }
    }
}
