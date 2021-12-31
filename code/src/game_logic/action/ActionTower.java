package game_logic.action;

import model.characters.monster.Monster;
import model.characters.Tower;

import static java.lang.Thread.sleep;

public class ActionTower implements Runnable{
    private Tower tower;

    public ActionTower(Monster m, Tower t){
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
