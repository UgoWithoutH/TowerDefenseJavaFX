package game_logic.tower.bullet;

import game_logic.GameObject;
import game_logic.Vector2;
import game_logic.enemy.Enemy;
import game_logic.logic_manager.BulletManager;

public class Bullet extends GameObject {
    protected Vector2 direction, targetPosition,startPosition;
    protected float speed;
    protected int damage;
    protected Enemy enemy;
    public BulletManager bulletManager;

    public Bullet(String name, Vector2 direction, Vector2 targetPosition, Vector2 startPosition, float speed, int damage, Enemy enemy, BulletManager bulletManager) {
        super(name,startPosition);
        this.direction = direction;
        this.targetPosition = targetPosition;
        this.startPosition = startPosition;
        this.speed = speed;
        this.damage = damage;
        this.enemy = enemy;
        this.bulletManager = bulletManager;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
