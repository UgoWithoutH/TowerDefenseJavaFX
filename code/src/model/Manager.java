package model;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import model.Serialization.GestionairePersistance;
import model.Serialization.StateSerializable;
import model.game_logic.GameManager;
import model.game_logic.GameState;
import view.ScreenController;

import java.io.*;

public class Manager {
    private GameManager gameManager;
    private ScoreRanking scoreRanking;

    public Manager(ScoreRanking scoreRanking){
        this.scoreRanking=scoreRanking;
        ScreenController.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                GestionairePersistance.saveStates(scoreRanking);
            }
        });
        GestionairePersistance.loadStates(scoreRanking);
    }

    public GameManager getGameManager() {return gameManager;}
    public void setGameManager(GameManager gameManager) {this.gameManager = gameManager;}
    public ScoreRanking getScoreRanking() {return scoreRanking;}
}
