package view.view_factory;

import model.characters.tower.Tower;

public interface TowerFactory {

    void createProjectiles();
    void createBuildProgressBar(double xCords, double yCords, Tower t);

}
