package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import model.game_logic.GameState;

import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public class ScoreRanking {
    private ObservableList<GameState> rankingObservable = FXCollections.observableArrayList();

    ListProperty<GameState> ranking = new SimpleListProperty<>(rankingObservable);
        public ObservableList<GameState> getRanking() {return ranking.get();}
        public ListProperty<GameState> rankingProperty() {return ranking;}
        public void setRanking(ObservableList<GameState> ranking) {this.ranking.set(ranking);}
    IntegerProperty numberScores = new SimpleIntegerProperty();
        public int getNumberScores() {return numberScores.get();}
        public IntegerProperty numberScoresProperty() {return numberScores;}
        public void setNumberScores(int numberScores) {this.numberScores.set(numberScores);}

    public ScoreRanking() {
        setNumberScores(10);
    }

    public void updateRanking(GameState gameState) {
        if(getNumberScores() == 0){
            rankingObservable.clear();
            return;
        }

        if (!rankingObservable.isEmpty()) {
            if (rankingObservable.size() >= getNumberScores()) {
                Collections.sort(rankingObservable);
                GameState lowerState = rankingObservable.get(rankingObservable.size() - 1);
                if (lowerState != gameState) {
                    rankingObservable.remove(lowerState);
                }
                rankingObservable.add(gameState);
                Collections.sort(rankingObservable);
            }
            else{
                rankingObservable.add(gameState);
                Collections.sort(rankingObservable);
            }
        }
        else{
            rankingObservable.add(gameState);
        }
    }
}
