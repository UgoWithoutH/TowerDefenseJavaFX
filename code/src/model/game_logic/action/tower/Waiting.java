package model.game_logic.action.tower;

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
            if(!tower.isBuildable()){
                sleep(tower.getBuildTimeSeconds()*1000);
                tower.setBuildable(true);
            }
            else {
                tower.setAttaker(false);
                sleep(2000);
                tower.setAttaker(true);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
