package model.game_logic;

public interface GameViewLogic {

    void createMonster(int health);
    void gameOver();
    void victory(GameState game);

}
