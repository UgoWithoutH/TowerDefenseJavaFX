package model.gamelogic.action.tower;

import model.characters.tower.Tower;

import static java.lang.Thread.sleep;

public class Waiting implements Runnable{
    private Tower tower;

    public Waiting(Tower t){
        tower = t;
    }

    @Override
    public void run() {
        try {
            if (!tower.isBuild()) {
                sleep(tower.getBuildTimeSeconds() * 1000L);        // conversion Second to Millis
                tower.setBuild(true);
            } else {
                tower.setAttacker(false);
                sleep(2000);
                tower.setAttacker(true);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
