package model.game_logic.action.tower;

import model.characters.monster.Monster;
import model.characters.tower.Tower;
import model.game_logic.GameState;

import java.util.Iterator;

public class Attacker {

    /**
     * Action Attack de toute les Tower du GameState
     *
     * @param game
     */
    public static void attack(GameState game) {
        Monster target;
        Waiting attackService;
        for (Tower tower : game.getPlayerTowers()) {
            if (tower.isAttaker()) {
                int towerMinXRange = tower.getX() - tower.getAttackRange();
                int towerMaxXRange = tower.getX() + tower.getAttackRange();
                int towerMinYRange = tower.getY() - tower.getAttackRange();
                int towerMaxYRange = tower.getY() + tower.getAttackRange();
                Iterator<Monster> iterator = game.getMonstersAlive().iterator();

                while (iterator.hasNext()) {
                    target = iterator.next();
                    if (target.getX() < towerMaxXRange & target.getX() > towerMinXRange & target.getY() > towerMinYRange & target.getY() < towerMaxYRange) {
                        attackService = new Waiting(tower);
                        Thread t = new Thread(attackService::run);
                        t.start();
                        if (tower.isBuild()) {
                            tower.createProjectile(target);
                            target.takeDamage(tower.getAttackDamage());
                        }
                        break;
                    }
                }
            }
        }
    }
}
