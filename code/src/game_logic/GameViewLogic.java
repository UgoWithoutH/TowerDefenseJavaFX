package game_logic;

import model.characters.tower.Tower;

public interface GameViewLogic {
    void createMonster(int health);

    void createProjectiles();

    void createBuildProgressBar(double xCords, double yCords, Tower t);

    //pas terminé
}
