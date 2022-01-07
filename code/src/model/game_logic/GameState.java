package model.game_logic;

import model.characters.monster.Monster;
import model.characters.tower.Tower;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;
import java.util.ArrayList;

/** TODO: 10/12/2021
 *   patron de conception ETAT a envisager
 */
public class GameState implements Serializable{

    private static GameState playerGame;
    private ArrayList<Tower> playerTowers; //faire list observable
    private ArrayList<Monster> monstersAlive;
    private boolean running = false;
    private boolean speed = false;
    private IntegerProperty level = new SimpleIntegerProperty();
        public int getLevel() {return level.get();}
        public IntegerProperty levelProperty() {return level;}
        public void setLevel(int level) {this.level.set(level);}
    private IntegerProperty lives = new SimpleIntegerProperty();
        public int getLives(){return lives.get();}
        public IntegerProperty livesProperty() {return lives;}
        public void setLives(int lives){this.lives.set(lives);}
    private IntegerProperty coins = new SimpleIntegerProperty();
        public int getCoins() {return coins.get();}
        public IntegerProperty coinsProperty() {return coins;}
        public void setCoins(int coins) {this.coins.set(coins);}
    private IntegerProperty score = new SimpleIntegerProperty();
        public int getScore() {return score.get();}
        public IntegerProperty scoreProperty() {return score;}
        public void setScore(int score) {this.score.set(score);}

    public GameState(){
        setCoins(100);
        setLevel(1);
        setScore(0);
        setLives(2);
        playerTowers = new ArrayList<Tower>();
        monstersAlive = new ArrayList<Monster>();
    }

    public static GameState getNewGame(){
        playerGame = new GameState();
        return playerGame;
    }

    public static GameState getGame() throws NullPointerException{
            return playerGame;
    }

    public boolean isSpeed() {return speed;}
    public void setSpeed(boolean speed) {this.speed = speed;}

    public boolean isRunning(){return running;}
    public void setRunning(boolean run){running = run;}

    public ArrayList<Tower> getPlayerTowers(){
        return playerTowers;
    }
    public ArrayList<Monster> getMonstersAlive() {
        return monstersAlive;
    }

    public void addTower(Tower tower){playerTowers.add(tower);}
}
