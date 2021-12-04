package game_logic.enemy;

import game_logic.Vector2;
import game_logic.logic_manager.EnemyManager;

public class Goblin extends Enemy {
    public static final float DEFAULT_LIFE = 100;
    public static final float DEFAULT_VELOCITY = 10;

    public Goblin(String name, Vector2 position, EnemyManager manager) {
        super(name,position,DEFAULT_LIFE,DEFAULT_VELOCITY,manager);
    }
}
