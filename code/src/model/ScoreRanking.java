package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import model.game_logic.GameState;

import java.util.HashSet;

public class ScoreRanking {
    private ObservableList<GameState> rankingObservable = FXCollections.observableArrayList();

    ListProperty<GameState> ranking = new SimpleListProperty<>(rankingObservable);
        public ObservableList<GameState> getRanking() {return ranking.get();}
        public ListProperty<GameState> rankingProperty() {return ranking;}
        public void setRanking(ObservableList<GameState> ranking) {this.ranking.set(ranking);}

    public void updateScore(GameState gameState) {
        if (!rankingObservable.isEmpty()) {
            if (rankingObservable.size() > 3) {
                GameState lowerState = gameState;
                for (GameState gm : rankingObservable) {
                    if (lowerState.getScore() > gm.getScore()) {
                        lowerState = gm;
                    }
                }
                if (lowerState != gameState) {
                    rankingObservable.remove(lowerState);
                    rankingObservable.add(gameState);
                }
            } else {
                rankingObservable.add(gameState);
            }
        } else {
            rankingObservable.add(gameState);
        }
    }

}
