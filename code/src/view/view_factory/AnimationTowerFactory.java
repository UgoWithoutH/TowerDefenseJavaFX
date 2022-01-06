package view.view_factory;

import model.characters.tower.Tower;

public interface AnimationTowerFactory {

    void createProjectiles();
    void createBuildProgressBar(double xCords, double yCords, Tower t);

}
