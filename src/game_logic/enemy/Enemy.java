package game_logic.enemy;

import game_logic.GameObject;
import game_logic.Vector2;

public abstract class Enemy extends GameObject {
    private float life;
    private float velocity;

    public Enemy(String name, Vector2 position, float life, float velocity){
        super(name,position);
        this.life=life;
        this.velocity=velocity;
    }
}
