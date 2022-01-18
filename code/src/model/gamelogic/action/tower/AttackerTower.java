package model.gamelogic.action.tower;

import javafx.collections.ObservableList;
import model.characters.Character;
import model.characters.monster.Monster;
import model.characters.tower.Tower;
import model.gamelogic.action.Attacker;

import java.util.Iterator;

public class AttackerTower implements Attacker {
    private ObservableList<Tower> listTower;
    private ObservableList<Character> listCharacter;

    public AttackerTower(ObservableList<Tower> listTower, ObservableList<Character> listCharacter) {
        this.listTower = listTower;
        this.listCharacter = listCharacter;
    }


    public void attack() {
        Character target;
        Waiting attackService;

        //if(game.isGameOver()) return;

        for (Tower tower : listTower) {
            if (tower.isAttaker()) {
                int towerMinXRange = tower.getX() - tower.getAttackRange();
                int towerMaxXRange = tower.getX() + tower.getAttackRange();
                int towerMinYRange = tower.getY() - tower.getAttackRange();
                int towerMaxYRange = tower.getY() + tower.getAttackRange();
                Iterator<Character> iterator = listCharacter.iterator();

                while (iterator.hasNext()) {
                    target = iterator.next();
                    if (target.getX() < towerMaxXRange & target.getX() > towerMinXRange & target.getY() > towerMinYRange & target.getY() < towerMaxYRange) {
                        attackService = new Waiting(tower);
                        Thread t = new Thread(attackService::run);
                        t.start();
                        if (tower.isBuild()) {
                            tower.createProjectile(target);
                            if(target instanceof Monster monster) {
                                monster.takeDamage(tower.getAttackDamage());
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
}
