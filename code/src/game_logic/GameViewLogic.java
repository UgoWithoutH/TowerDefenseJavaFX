package game_logic;

import javafx.scene.Group;
import model.characters.tower.Tower;

public interface GameViewLogic {

    void createMonster(int health);
    void gameOver();

}
