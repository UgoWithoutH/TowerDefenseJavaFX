package game_logic.tower;

import game_logic.Vector2;

public class ArcherTower extends Tower {
    private static final int DEFAULT_RANGE = 10;
    private static final BulletType DEFAULT_BULLETTYPE = BulletType.ARROW;
    private static final int DEFAULT_COST = 1000;
    private static final String DEFAULT_NAME = "archer tower";

    public ArcherTower(String name,Vector2 position, int range,BulletType bulletType, int attackRate, int cost) {
        super(DEFAULT_NAME,position, Tower.DEFAULT_LEVEL, Tower.DEFAULT_TOWERSTATE, DEFAULT_RANGE, DEFAULT_BULLETTYPE, attackRate, DEFAULT_COST);
    }

    public int calculateDamage(){
        return 50;
    }
}

