package game_logic;

import model.characters.Monster;
import model.characters.Tower;

import static java.lang.Thread.sleep;

public class AttackService implements Runnable{
    private Tower tower;

    public AttackService(Monster m, Tower t){
        tower = t;
    }

    @Override
    public void run() {
        try {
            tower.setAttaker(false);
            sleep(2000);
            tower.setAttaker(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
