package game_logic.enemy;

import game_logic.GameObject;
import game_logic.Vector2;

public class Goblin extends Enemy {
    public static final float DEFAULT_LIFE = 100;
    public static final float DEFAULT_VELOCITY = 10;

    public Goblin(String name, Vector2 position) {
        super(name,position,DEFAULT_LIFE,DEFAULT_VELOCITY);
    }

}
