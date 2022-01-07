package model;

import model.game_logic.GameManager;

public class Manager {
    GameManager gameManager;
    ScoreRanking scoreRanking;

    public Manager(ScoreRanking scoreRanking){
        this.scoreRanking=scoreRanking;
    }

    public GameManager getGameManager() {return gameManager;}
    public void setGameManager(GameManager gameManager) {this.gameManager = gameManager;}
    public ScoreRanking getScoreRanking() {return scoreRanking;}
}
