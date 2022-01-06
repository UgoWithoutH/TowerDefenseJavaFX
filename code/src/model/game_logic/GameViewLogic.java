package model.game_logic;

import model.ScoreRanking;

public interface GameViewLogic {

    void createMonster(int health);
    void gameOver();
    void victory(GameState game);

}
