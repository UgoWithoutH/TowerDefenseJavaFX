package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.gamelogic.GameState;

import java.util.Collections;

public class ScoreRanking {
    private final ObservableList<GameState> rankingObservable = FXCollections.observableArrayList();

    private ListProperty<GameState> ranking = new SimpleListProperty<>(rankingObservable);
        public ObservableList<GameState> getRanking() {return ranking.get();}
        public ListProperty<GameState> rankingProperty() {return ranking;}
        public void setRanking(ObservableList<GameState> ranking) {this.ranking.set(ranking);}
    private IntegerProperty numberScores = new SimpleIntegerProperty();
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
            }
        }
        rankingObservable.add(gameState);
        if(rankingObservable.size() > 1){
            Collections.sort(rankingObservable);
        }
    }
}
