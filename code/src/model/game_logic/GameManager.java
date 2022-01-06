package model.game_logic;

import model.boucleJeu.Boucle;
import model.boucleJeu.Observateur;
import model.game_logic.action.ActionTower;
import model.characters.monster.Basic;
import model.characters.monster.Monster;
import model.characters.monster.Speed;
import model.characters.tower.ClassicTower;
import model.update.DrawMap;
import model.Map.Map;
import model.characters.tower.Tower;
import view.view_factory.AnimationBasicTowerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class GameManager implements Observateur {

    private Map gameMap;
    private GameState game;
    private GameViewLogic gameViewLogic;
    private Boucle boucle;
    private DrawMap drawMap;
    private Thread boucleThread;
    private Scanner enemyFile;
    private AnimationBasicTowerFactory basicTowerFactory;

    public GameManager(GameViewLogic gameViewLogic, Map map) throws FileNotFoundException{
        this.gameViewLogic = gameViewLogic;
        this.gameMap = map;
        game = GameState.getNewGame();
        game.setRunning(true);
        Monster.setPath(gameMap.getPath());
        enemyFile = new Scanner(new File(System.getProperty("user.dir")+ "/code/ressources/Level/Level1/EnemyFile.txt"));
        boucle = new Boucle(this);
        basicTowerFactory = new AnimationBasicTowerFactory(this);
    }

    public AnimationBasicTowerFactory getBasicTowerFactory() {return basicTowerFactory;}

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

    public Map getGameMap() {
        return gameMap;
    }

    public void start(){
        boucleThread = new Thread(boucle);
        boucleThread.start();
    }

    @Override
    public void update(int timer) {
        try {
            if(!enemyFile.hasNextLine() && game.getMonstersAlive().isEmpty() && game.isRunning()){
                victory();
            }

            if(timer%40 == 0 && enemyFile.hasNextLine()) {
                spawnEnemy(enemyFile.nextLine());
            }
            else if(timer <= 0){
                game.setLevel(game.getLevel() + 1);
            }
            updateLocations();
            attacker();
            basicTowerFactory.createProjectiles();
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

    public void updateStates(Monster monster){
        if(game.isRunning()) {
            if (monster.isPathFinished()) {
                game.setLives((game.getLives()) - 1);
            } else {
                game.setCoins((game.getCoins()) + monster.getReward());
                game.setScore(game.getScore() + (monster.getReward() * game.getLevel()));
            }
        }
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
        game.setRunning(false);
        gameViewLogic.victory(game);
    }

    public void gameOver(){
        var listMonster = game.getMonstersAlive();
        for(Monster monster : listMonster){
            monster.getView().setVisible(false);
        }
        game.getMonstersAlive().clear();
        game.setRunning(false);
        gameViewLogic.gameOver();
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
                basicTowerFactory.createBuildProgressBar(xCords,yCords,tower);
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
