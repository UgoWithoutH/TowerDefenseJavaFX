package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import model.serialization.AdministratorPersistence;
import model.serialization.AdministratorPersistenceBinary;
import model.gamelogic.GameManager;
import view.ScreenController;

public class Manager {
    private GameManager gameManager;
    private ScoreRanking scoreRanking;
    private AdministratorPersistence administratorPersistence;
    private StringProperty pseudo = new SimpleStringProperty();
        public String getPseudo() {return pseudo.get();}
        public StringProperty pseudoProperty() {return pseudo;}
        public void setPseudo(String pseudo) {this.pseudo.set(pseudo);}

    public Manager(ScoreRanking scoreRanking){
        this.scoreRanking=scoreRanking;
        administratorPersistence = new AdministratorPersistenceBinary();
        ScreenController.getStage().setOnCloseRequest(event -> administratorPersistence.save(scoreRanking));
        administratorPersistence.load(scoreRanking);
    }

    public GameManager getGameManager() {return gameManager;}
    public void setGameManager(GameManager gameManager) {this.gameManager = gameManager;}
    public ScoreRanking getScoreRanking() {return scoreRanking;}
}
