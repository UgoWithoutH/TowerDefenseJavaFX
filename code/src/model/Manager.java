package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.gamelogic.GameManager;
import model.serialization.GestionairePersistance;
import view.ScreenController;

public class Manager {
    private GameManager gameManager;
    private ScoreRanking scoreRanking;
    private StringProperty pseudo = new SimpleStringProperty();
        public String getPseudo() {return pseudo.get();}
        public StringProperty pseudoProperty() {return pseudo;}
        public void setPseudo(String pseudo) {this.pseudo.set(pseudo);}

    public Manager(ScoreRanking scoreRanking){
        this.scoreRanking=scoreRanking;
        ScreenController.getStage().setOnCloseRequest(event -> GestionairePersistance.saveStates(scoreRanking));
        GestionairePersistance.loadStates(scoreRanking);
    }

    public GameManager getGameManager() {return gameManager;}
    public void setGameManager(GameManager gameManager) {this.gameManager = gameManager;}
    public ScoreRanking getScoreRanking() {return scoreRanking;}
}
