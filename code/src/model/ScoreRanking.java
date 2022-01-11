package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.game_logic.GameState;

public class ScoreRanking {
    private ObservableList<GameState> rankingObservable = FXCollections.observableArrayList();

    ListProperty<GameState> ranking = new SimpleListProperty<>(rankingObservable);
        public ObservableList<GameState> getRanking() {return ranking.get();}
        public ListProperty<GameState> rankingProperty() {return ranking;}
        public void setRanking(ObservableList<GameState> ranking) {this.ranking.set(ranking);}

    public void updateRanking(GameState gameState) {
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
