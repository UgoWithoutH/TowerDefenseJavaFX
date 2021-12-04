package game_logic.enemy;

import game_logic.GameObject;
import game_logic.Vector2;
import game_logic.logic_manager.EnemyManager;

public abstract class Enemy extends GameObject {
    private float life;
    private float velocity;
    private EnemyManager enemyManager;

    public Enemy(String name, Vector2 position, float life, float velocity, EnemyManager manager){
        super(name,position);
        this.life=life;
        this.velocity=velocity;
        enemyManager = manager;
    }

    public float getLife() { return life; }

    public void setLife(float life) {
        if(life > 0)
            this.life = life;
        else
            this.life = 0;
    }

    public void hit(float damage){
        setLife(life - damage);
        if (life == 0){
            enemyManager.EnemyDie(this);
        }
    }
}
