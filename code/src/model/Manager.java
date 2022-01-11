package model;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import model.game_logic.GameManager;
import model.game_logic.GameState;
import view.ScreenController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;

public class Manager {
    GameManager gameManager;
    ScoreRanking scoreRanking;

    public Manager(ScoreRanking scoreRanking){
        this.scoreRanking=scoreRanking;
        ScreenController.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("fermé");
                serializationStates();
            }
        });
    }

    public GameManager getGameManager() {return gameManager;}
    public void setGameManager(GameManager gameManager) {this.gameManager = gameManager;}
    public ScoreRanking getScoreRanking() {return scoreRanking;}

    public void serializationStates(){
        try {
            File fichier = new File(String.valueOf(getClass().getResource("/serialization/test.ser").toURI().toURL()));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));

            for (GameState game : scoreRanking.getRanking()) {
                GameState gameStateSerialization = new GameState();
                gameStateSerialization.setLevel(game.getLevel());
                gameStateSerialization.setScore(game.getLevel());
                gameStateSerialization.setTimeSeconds(game.getTimeSeconds());
                oos.writeObject(gameStateSerialization);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
