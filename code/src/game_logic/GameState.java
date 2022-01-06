package game_logic;

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
    private int level;
    private ArrayList<Tower> playerTowers;
    private ArrayList<Monster> monstersAlive;
    private boolean running = false;
    private boolean speed = false;
    private boolean gameOver = false;

    private IntegerProperty lives = new SimpleIntegerProperty();
    public int getLives(){return lives.get();}
    public void setLives(int lives){this.lives.set(lives);}
    public IntegerProperty livesProperty() {return lives;}

    private IntegerProperty coins = new SimpleIntegerProperty(); //property pour binding
    public int getCoins() {return coins.get();}
    public void setCoins(int coins) {this.coins.set(coins);}
    public IntegerProperty coinsProperty() {return coins;}

    private IntegerProperty score = new SimpleIntegerProperty(); //property pour binding
    public int getScore() {return score.get();}
    public void setScore(int score) {this.score.set(score);}
    public IntegerProperty scoreProperty() {return score;}

    public GameState(){
        setCoins(100);
        level = 1;
        score.set(0); // property
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

    public boolean isGameOver() {return gameOver;}
    public void setGameOver(boolean gameOver) {this.gameOver = gameOver;}

    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }

    public ArrayList<Tower> getPlayerTowers(){
        return playerTowers;
    }
    public ArrayList<Monster> getMonstersAlive() {
        return monstersAlive;
    }

    public void addTower(Tower tower){playerTowers.add(tower);}
}
