package model.gamelogic.action.tower;

import model.characters.tower.Tower;

import static java.lang.Thread.sleep;

public class WaitingBuild implements Runnable{
    private Tower tower;

    public WaitingBuild(Tower t){
        tower = t;
    }

    @Override
    public void run() {
        try {
            if (!tower.isBuild()) {
                sleep(tower.getBuildTimeSeconds() * 1000);        // conversion Second to Millis
                tower.setBuild(true);
            } else {
                tower.setAttaker(false);
                sleep(2000);
                tower.setAttaker(true);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
