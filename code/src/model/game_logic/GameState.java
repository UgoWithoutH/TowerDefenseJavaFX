package model.game_logic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.characters.monster.Monster;
import model.characters.tower.Tower;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;
import java.util.ArrayList;

/** TODO: 10/12/2021
 *   patron de conception ETAT a envisager
 */
public class GameState{

    private static GameState playerGame;
    private ObservableList<Tower> playerTowers;
    private ObservableList<Monster> monstersAlive;
    private boolean speed = false;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty();
        public int getTimeSeconds() {return timeSeconds.get();}
        public IntegerProperty timeSecondsProperty() {return timeSeconds;}
        public void setTimeSeconds(int timeSeconds) {this.timeSeconds.set(timeSeconds);}
    private BooleanProperty gameOver = new SimpleBooleanProperty();
        public boolean isGameOver() {return gameOver.get();}
        public BooleanProperty gameOverProperty() {return gameOver;}
        public void setGameOver(boolean gameOver) {this.gameOver.set(gameOver);}
    private BooleanProperty victory = new SimpleBooleanProperty();
        public boolean isVictory() {return victory.get();}
        public BooleanProperty victoryProperty() {return victory;}
        public void setVictory(boolean victory) {this.victory.set(victory);}
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
        setTimeSeconds(0);
        setCoins(100);
        setLevel(1);
        setScore(0);
        setLives(2);
        setVictory(false);
        playerTowers = FXCollections.observableArrayList();
        monstersAlive = FXCollections.observableArrayList();
    }

    public static GameState getGame() throws NullPointerException{
            return playerGame;
    }

    public boolean isSpeed() {return speed;}
    public void setSpeed(boolean speed) {this.speed = speed;}

    public ObservableList<Tower> getPlayerTowers(){
        return playerTowers;
    }
    public ObservableList<Monster> getMonstersAlive() {
        return monstersAlive;
    }

    public void addTower(Tower tower){playerTowers.add(tower);}
}
