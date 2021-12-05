package game_logic.tower.bullet;

import game_logic.Vector2;
import game_logic.enemy.Enemy;
import game_logic.logic_manager.BulletManager;
import game_logic.tower.Tower;

public class Arrow extends Bullet{
    private static final String DEFAULT_NAME = "ARROW";
    private static final int DEFAULT_SPEED_LVL1 = 5;
    private static final int DEFAULT_SPEED_LVL2 = 10;
    private static final int DEFAULT_SPEED_LVL3 = 15;

    public Arrow(Vector2 direction, Vector2 targetPosition, Vector2 startPosition, int damage, Enemy enemy, BulletManager bulletManager, Tower tower) {
        super(DEFAULT_NAME, direction, targetPosition, startPosition, damage, enemy, bulletManager,tower);

        switch (tower.getCurrentlevel()){
            case 1 :
                super.setSpeed(DEFAULT_SPEED_LVL1);
                break;
            case 2 :
                super.setSpeed(DEFAULT_SPEED_LVL2);
                break;
            case 3 :
                super.setSpeed(DEFAULT_SPEED_LVL3);
                break;
        }
    }
}
