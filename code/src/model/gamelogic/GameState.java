package model.gamelogic;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.characters.Character;
import model.characters.tower.Tower;

import java.util.Objects;

/**
 * States relatifs à la partie
 */
public class GameState implements Comparable<GameState>{

    private final ObservableList<Tower> playerTowers = FXCollections.observableArrayList();
    private final ObservableList<Character> charactersAlive = FXCollections.observableArrayList();
    private boolean speed = false;
    private boolean removeCharacter = false;
    private StringProperty pseudo = new SimpleStringProperty();
        public String getPseudo() {return pseudo.get();}
        public StringProperty pseudoProperty() {return pseudo;}
        public void setPseudo(String pseudo) {this.pseudo.set(pseudo);}
    private IntegerProperty timeSeconds = new SimpleIntegerProperty();
        public int getTimeSeconds() {return timeSeconds.get();}
        public IntegerProperty timeSecondsProperty() {return timeSeconds;}
        public void setTimeSeconds(int timeSeconds) {this.timeSeconds.set(timeSeconds);}
    private BooleanProperty gameOver = new SimpleBooleanProperty();
        public boolean isGameOver() {return gameOver.get();}
        public BooleanProperty gameOverProperty() {return gameOver;}
        public void setGameOver(boolean gameOver) {this.gameOver.set(gameOver);}
    private BooleanProperty victory = new SimpleBooleanProperty(false);
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

    /**
     * Ressources du joueur et states de la Partie
     * @param pseudo    String Pseudo
     */
    public GameState(String pseudo){
        setPseudo(pseudo);
        setTimeSeconds(0);
        setCoins(50);
        setLevel(1);
        setScore(0);
        setLives(2);
        setVictory(false);
    }

    public boolean isRemoveCharacter() {return removeCharacter;}
    public void setRemoveCharacter(boolean value){
        removeCharacter = value;}

    public boolean isSpeed() {return speed;}
    public void setSpeed(boolean speed) {this.speed = speed;}

    public ObservableList<Tower> getPlayerTowers(){
        return playerTowers;
    }
    public ObservableList<Character> getCharactersAlive() {
        return charactersAlive;
    }

    public void addTower(Tower tower){playerTowers.add(tower);}

    @Override
    public int compareTo(GameState g) {
        int coeff = g.isGameOver() ? 100 : 1;

        if(this.getPseudo().compareTo(g.getPseudo()) == 0) {
            return ((this.getLevel() - g.getLevel()*coeff) +
                    (this.getScore() - g.getScore()) +
                    (this.getTimeSeconds()) - g.getTimeSeconds());
        }
        else{
            return ((this.getLevel() - g.getLevel()*coeff) +
                    (this.getScore() - g.getScore()) +
                    (this.getTimeSeconds()) - g.getTimeSeconds() +
                    this.getPseudo().compareTo(g.getPseudo()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return pseudo.equals(gameState.pseudo) && timeSeconds.equals(gameState.timeSeconds) && victory.equals(gameState.victory) && level.equals(gameState.level) && score.equals(gameState.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pseudo, timeSeconds, victory, level, score);
    }
}
