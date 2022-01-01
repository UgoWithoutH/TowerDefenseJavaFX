package game_logic;

import boucleJeu.Boucle;
import boucleJeu.Observateur;
import game_logic.action.ActionTower;
import model.characters.monster.Basic;
import model.characters.monster.Monster;
import model.characters.monster.Speed;
import update.DrawMap;
import model.Map.Map;
import model.characters.Tower;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class GameManager implements Observateur {

    private Map gameMap;
    private GameState game;
    private GameViewLogic gameViewLogic;
    private ArrayList<Tower> playerTowers;
    private ArrayList<Monster> monstersAlive;
    private Boucle boucle;
    private DrawMap drawMap;
    private Thread boucleThread;
    public boolean freeze = false; //test
    private Scanner enemyFile;



    public GameManager() throws FileNotFoundException {
        playerTowers = new ArrayList<Tower>();
        monstersAlive = new ArrayList<Monster>();
        boucle = new Boucle(this);
    }

    public Thread getBoucleThread(){return boucleThread;}

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

    public void initialize(GameViewLogic gameViewLogic, Map map) throws java.io.IOException, URISyntaxException {
        this.gameViewLogic = gameViewLogic;
        this.gameMap = map;
        game = GameState.getNewGame();
        game.setScore(200);
        Monster.setPath(gameMap.getPath());
        enemyFile = new Scanner(new File(System.getProperty("user.dir")+ "/code/ressources/Level/Level1/EnemyFile.txt"));
    }

    public void start(){
        boucleThread = new Thread(boucle);
        boucleThread.start();
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
            if( timer%40 == 0 && enemyFile.hasNextLine()) {
                spawnEnemy(enemyFile.nextLine());
            }
            else if(timer <= 0){
                game.setLevel(game.getLevel() + 1);
            }
            updateLocations();
            attacker();
            gameViewLogic.createProjectiles();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void spawnEnemy(String type) throws CloneNotSupportedException {
        switch (type) {
            case "Basic":
                game.getMonstersAlive().add(new Basic(5));
                gameViewLogic.createMonster(5);
                break;
            case "Speed":
                game.getMonstersAlive().add(new Speed(3));
                gameViewLogic.createMonster(3);
                break;

            default:
                game.getMonstersAlive().add(new Basic(3));
                gameViewLogic.createMonster(3);
                break;
        }
    }

    public void removeMonster(Monster monster){
        game.getMonstersAlive().remove(monster);
        monster.getView().setVisible(false);
    }

    public synchronized void updateStates(Monster monster){
        if(!game.isGameOver()) {
            if (monster.isPathFinished()) {
                game.setLives((game.getLives()) - 1);
            } else {
                game.setCoins((game.getCoins()) + monster.getReward());
                game.setScore(game.getScore() + (monster.getReward() * game.getLevel()));
            }
        }
        //monster.getView().setVisible(false);
        //game.getMonstersAlive().remove(monster);
    }

    private void updateLocations(){
        ArrayList<Monster> monsterEnd = new ArrayList<>();
        ArrayList<Monster> monsterListToDelete;
        if(!game.getMonstersAlive().isEmpty()){
            Iterator<Monster> monsters = game.getMonstersAlive().iterator();
            Monster monster;
            while(monsters.hasNext()) {
                monster = monsters.next();
                monster.updateLocation(monster.getMovementSpeed());
                if(monster.isPathFinished()){
                    updateStates(monster);
                    monsterEnd.add(monster);
                    if(game.getLives() == 0){
                        game.setGameOver(true);
                        gameOver();
                        return;
                    }
                }
            }
            for(Monster m : monsterEnd){
                game.getMonstersAlive().remove(m);
                m.getView().setVisible(false);
            }
        }
    }

    public void gameOver(){
        var listMonster = game.getMonstersAlive();
        for(Monster monster : listMonster){
            monster.getView().setVisible(false);
        }
        gameViewLogic.gameOver();
    }

    public void buyTower(double xCords , double yCords){
        int xTile = (int)(xCords / 64);
        int yTile = (int)(yCords / 64);

        if(gameMap.nodeOpen(xTile,yTile)){
            Tower tower = new Tower(xTile, yTile);
            if(game.getCoins() >= tower.getSellCost()) {
                game.addTower(tower);
                game.setCoins(game.getCoins() - 50);
                gameMap.setMapNode(((int) (xCords / 64)), ((int) (yCords / 64)), 7);
                drawMap.draw(gameMap);
                gameViewLogic.createBuildProgressBar(xCords,yCords,tower);
            }
        }
    }

    public void attacker() throws InterruptedException {
        Monster target;
        ActionTower attackService;
        ArrayList<Monster> list = new ArrayList<>();
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
                        attackService = new ActionTower(target, tower);
                        Thread t = new Thread(attackService::run);
                        t.start();
                        if(tower.isBuildable()) {
                            tower.createProjectile(target);
                            target.takeDamage(tower.getAttackDamage());
                            list.add(target);
                        }
                        break;
                    }
                }
            }
        }
    }
}
