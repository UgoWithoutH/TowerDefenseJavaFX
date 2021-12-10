package game_logic;

import game_logic.engine.characters.Monster;
import game_logic.engine.characters.Tower;

import java.io.Serializable;
import java.util.ArrayList;

/** TODO: 10/12/2021
 *   patron de conception ETAT a envisager
 */
public class GameState implements Serializable{

    //game state flags
    public static final int IS_RUNNING = 1;
    public static final int IS_PAUSED = 2;
    public static final int IS_STOPPED = 3;

    private static GameState playerGame;
    private int state;
    private int resources;
    private int level;
    private int score;
    private int lives;
    private ArrayList<Tower> playerTowers;
    private ArrayList<Monster> monstersAlive;


    //CONSTRUCTORS
    private GameState(){
        state = IS_RUNNING;
        resources = 10000;
        level = 0;
        score = 0;
        lives = 20;
        playerTowers = new ArrayList<Tower>();
        monstersAlive = new ArrayList<Monster>();
    }

    //Overwrites current Game State
    public static GameState getNewGame(){
        playerGame = new GameState();
        return playerGame;
    }

    //Throws null exception if new game is never created
    public static GameState getGame() throws NullPointerException{
            return playerGame;
    }

    //SETTERS
    public void setResources(int resources){
        this.resources = resources;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public void setScore(int score){
        this.score = score;
    }
    public void setLives(int lives){
        this.lives = lives;
    }
    public void setState(int state) {
        this.state = state;
    }

    //GETTERS
    public int getResources(){
        return resources;
    }
    public int getLevel(){
        return level;
    }
    public int getScore(){
        return score;
    }
    public int getLives() {
        return lives;
    }
    public int getState() {
        return state;
    }

    public boolean isPaused(){
        if(state == IS_PAUSED){
            return true;
        }
        return false;
    }

    public boolean isRunning(){
        if(state == IS_RUNNING){
            return true;
        }
        return false;
    }

    public boolean isStopped(){
        if(state == IS_PAUSED){
            return true;
        }
        return false;
    }

    public ArrayList<Tower> getPlayerTowers(){
        return playerTowers;
    }

    public ArrayList<Monster> getMonstersAlive() {
        return monstersAlive;
    }

    public void addMonster(Monster monster){monstersAlive.add(monster);}
    public void addTower(Tower tower){playerTowers.add(tower);}
    public void removeMonster(Monster monster){monstersAlive.remove(monster);}
    public void removeTower(Tower tower){playerTowers.remove(tower);}
}
