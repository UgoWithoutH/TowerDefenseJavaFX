package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.serialization.AdministratorPersistence;
import model.serialization.AdministratorPersistenceBinary;
import model.gamelogic.GameManager;
import view.ScreenController;

/**
 * Gestion de l'application
 */
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
        ScreenController.getStage().setOnCloseRequest(event -> saveStates());
        administratorPersistence.load(scoreRanking);
    }

    /**
     * Sauvegarde le Score de la partie
     */
    public void saveStates(){
        administratorPersistence.save(scoreRanking);
    }

    public GameManager getGameManager() {return gameManager;}
    public void setGameManager(GameManager gameManager) {this.gameManager = gameManager;}

    public ScoreRanking getScoreRanking() {return scoreRanking;}
}
