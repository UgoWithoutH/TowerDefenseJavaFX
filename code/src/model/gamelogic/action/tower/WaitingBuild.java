package model.gamelogic.action.tower;

import model.characters.tower.Tower;

import static java.lang.Thread.sleep;

public class WaitingBuild implements Runnable{
    private Tower tower;

    /**
     * Cree une attente pour une construction de Tour
     * @param t
     */
    public WaitingBuild(Tower t){
        tower = t;
    }

    /**
     * Verifie si la Tower est en construction et définit si elle est libre pour attacker
     */
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
