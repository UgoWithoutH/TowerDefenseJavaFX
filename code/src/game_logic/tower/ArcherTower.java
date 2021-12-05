package game_logic.tower;

import game_logic.Vector2;

public class ArcherTower extends Tower {
    private static final int DEFAULT_RANGE = 10;
    private static final int DEFAULT_COST = 1000;
    private static final String DEFAULT_NAME = "archer tower";
    private static final int DEFAULT_ATTACKRATE = 10;

    public ArcherTower(Vector2 position) {
        super(DEFAULT_NAME,position, DEFAULT_RANGE, DEFAULT_ATTACKRATE, DEFAULT_COST);
    }

    public int calculateDamage(){
        return 50;
    }


}

