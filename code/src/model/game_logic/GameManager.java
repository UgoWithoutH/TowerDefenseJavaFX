package model.game_logic;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.boucleJeu.Boucle;
import model.boucleJeu.Observateur;
import model.game_logic.action.ActionTower;
import model.characters.monster.Basic;
import model.characters.monster.Monster;
import model.characters.monster.Speed;
import model.characters.tower.ClassicTower;
import model.Map.update.DrawMap;
import model.Map.Map;
import model.characters.tower.Tower;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class GameManager implements Observateur {

    private Map gameMap;
    private GameState game;
    private Boucle boucle;
    private DrawMap drawMap;
    private Thread boucleThread;
    private Scanner enemyFile;
    private boolean removeMonster = false;

    public GameManager(Map map) throws FileNotFoundException{
        this.gameMap = map;
        game = new GameState();
        Monster.setPath(gameMap.getPath());
        enemyFile = new Scanner(new File(System.getProperty("user.dir")+ "/code/ressources/Level/Level1/EnemyFile.txt"));
        boucle = new Boucle();
        boucle.subscribe(this);
    }

    public boolean isRemoveMonster(){return removeMonster;}

    public Boucle getBoucle(){ return boucle; }

    public Thread getBoucleThread(){ return boucleThread; }

    public DrawMap getDrawMap() {
        return drawMap;
    }
    public void setDrawMap(DrawMap drawMap) {
        this.drawMap = drawMap;
    }

    public GameState getGame(){
        return game;
    }

    public Map getGameMap() {
        return gameMap;
    }

    public Scanner getEnemyFile(){return enemyFile;}

    public void start(){
        boucle.setRunning(true);
        boucleThread = new Thread(boucle);
        boucleThread.start();
    }

    @Override
    public void update(int timer) {
        try {
            var timeMilis = timer * boucle.getDefaultMilis();
            var timeSeconds = (int) (timeMilis/1000);
            System.out.println(game.getTimeSeconds());
            if(timeSeconds != game.getTimeSeconds()){
                game.setTimeSeconds(game.getTimeSeconds()+1);
            }

            if(!enemyFile.hasNextLine() && game.getMonstersAlive().isEmpty() && boucle.isRunning()){
                victory();
            }

            if(timer%40 == 0 && enemyFile.hasNextLine()) {
                spawnEnemy(enemyFile.nextLine());
            }
            updateLocations();
            attacker();
        } catch (InterruptedException | CloneNotSupportedException e) {e.printStackTrace();}
    }

    public void spawnEnemy(String type) throws CloneNotSupportedException {
        switch (type) {
            case "Basic":
                game.getMonstersAlive().add(new Basic(5));
                break;
            case "Speed":
                game.getMonstersAlive().add(new Speed(3));
                break;

            default:
                game.getMonstersAlive().add(new Basic(3));
                break;
        }
    }

    public void removeMonster(Monster monster){
        removeMonster = true;
        game.getMonstersAlive().remove(monster);
        removeMonster = false;
        monster.getView().setVisible(false);
    }

    public void updateStates(Monster monster){
        if(boucle.isRunning()) {
            if (monster.isPathFinished()) {
                game.setLives((game.getLives()) - 1);
            } else {
                game.setCoins((game.getCoins()) + monster.getReward());
                game.setScore(game.getScore() + (monster.getReward() * (game.getLevel() + 1)));
            }
        }
    }

    private void updateLocations(){
        ArrayList<Monster> monsterEnd = new ArrayList<>();
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

    public void victory(){
        boucle.setRunning(false);
        game.setVictory(true);
    }

    public void gameOver(){
        var listMonster = game.getMonstersAlive();
        for(Monster monster : listMonster){
            monster.getView().setVisible(false);
        }
        game.getMonstersAlive().clear();
        boucle.setRunning(false);
        game.setGameOver(true);
        //gameViewLogic.gameOver();
    }

    public void buyTower(double xCords , double yCords){
        int xTile = (int)(xCords / 64);
        int yTile = (int)(yCords / 64);

        if(gameMap.nodeOpen(xTile,yTile)){
            Tower tower = new ClassicTower(xTile, yTile);
            if(game.getCoins() >= tower.getSellCost()) {
                game.addTower(tower);
                game.setCoins(game.getCoins() - 50);
                gameMap.setMapNode(((int) (xCords / 64)), ((int) (yCords / 64)), 7);
                drawMap.draw(gameMap);
            }
        }
    }

    public void attacker() throws InterruptedException {
        Monster target;
        ActionTower attackService;
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
                        }
                        break;
                    }
                }
            }
        }
    }
}
