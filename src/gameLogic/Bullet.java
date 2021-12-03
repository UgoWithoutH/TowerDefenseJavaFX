package gameLogic;

import gameLogic.Vector2;
import gameLogic.logicManager.BulletManager;

public class Bullet extends GameObject{
    protected Vector2 direction, targetPosition,startPosition;
    protected float speed;
    protected int damage;
    protected Enemy enemy;
    public BulletManager bulletManager;

    public Bullet(Vector2 direction, Vector2 targetPosition, Vector2 startPosition, float speed, int damage, Enemy enemy, BulletManager bulletManager) {
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
