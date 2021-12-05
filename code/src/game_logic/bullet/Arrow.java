package game_logic.bullet;

import game_logic.Vector2;
import game_logic.tower.Tower;

public class Arrow extends Bullet{
    private static final String DEFAULT_NAME = "ARROW";
    private static final int DEFAULT_SPEED_LVL1 = 5;
    private static final int DEFAULT_SPEED_LVL2 = 10;
    private static final int DEFAULT_SPEED_LVL3 = 15;

    private static final int DEFAULT_DAMAGE_LVL1 = 5;
    private static final int DEFAULT_DAMAGE_LVL2 = 7;
    private static final int DEFAULT_DAMAGE_LVL3 = 9;
    public Arrow(Vector2 startPosition, Tower tower) {
        super(DEFAULT_NAME, startPosition, DEFAULT_SPEED_LVL1, DEFAULT_DAMAGE_LVL1, tower);
    }


    @Override
    public void upgrade() {
        switch (super.getTower().getCurrentlevel()){
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
