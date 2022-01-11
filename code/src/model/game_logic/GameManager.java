package model.game_logic;

import model.boucleJeu.Boucle;
import model.boucleJeu.Observateur;
import model.characters.monster.Monster;
import model.Map.update.DrawMap;
import model.Map.Map;
import model.game_logic.action.states.Update;
import model.game_logic.action.tower.Attacker;
import model.game_logic.action.monster.Displacer;
import model.game_logic.action.monster.Spawner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GameManager implements Observateur {

    private Map gameMap;
    private GameState game;
    private Boucle boucle;
    private DrawMap drawMap;
    private Thread boucleThread;
    private Scanner enemyFile;

    public GameManager(Map map) throws FileNotFoundException{
        this.gameMap = map;
        game = new GameState();
        Monster.setPath(gameMap.getPath());
        enemyFile = new Scanner(new File(System.getProperty("user.dir")+ "/code/ressources/Level/Level1/EnemyFile.txt"));
        boucle = new Boucle();
        boucle.subscribe(this);
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

    public Map getGameMap() {
        return gameMap;
    }

    public void start(){
        boucle.setRunning(true);
        boucleThread = new Thread(boucle);
        boucleThread.start();
    }

    @Override
    public void update(int timer) {
        try {
            if (boucle.isRunning()) {
                Update.updateTimerSeconds(timer,boucle.getDefaultMilis(),game);
                if (!enemyFile.hasNextLine() && game.getMonstersAlive().isEmpty() && boucle.isRunning()) {
                    victory();
                }

                if (timer % 40 == 0 && enemyFile.hasNextLine()) {
                    Spawner.spawnEnemy(enemyFile.nextLine(), game);
                }
                if(!Displacer.updateLocations(game)){
                    gameOver();
                }
                else {
                    Attacker.attack(game);
                }
            }
        } catch(InterruptedException | CloneNotSupportedException e){
            e.printStackTrace();
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
        game.setRemoveMonster(true);
        game.getMonstersAlive().clear();
        boucle.setRunning(false);
        game.setGameOver(true);
    }
}
