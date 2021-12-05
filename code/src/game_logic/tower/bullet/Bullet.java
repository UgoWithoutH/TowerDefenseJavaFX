package game_logic.tower.bullet;

import game_logic.GameObject;
import game_logic.Vector2;
import game_logic.enemy.Enemy;
import game_logic.logic_manager.BulletManager;
import game_logic.tower.Tower;

public abstract class Bullet extends GameObject {// on met les attributs private je pense pour l'instant on adaptera par la suite
    private Vector2 direction, targetPosition,startPosition;
    private float speed;
    private int damage;
    private Enemy enemy;
    private BulletManager bulletManager;
    private Tower tower;

    public Bullet(String name, Vector2 direction, Vector2 targetPosition, Vector2 startPosition, int damage, Enemy enemy, BulletManager bulletManager, Tower tower) {
        super(name,startPosition);
        this.direction = direction;
        this.targetPosition = targetPosition;
        this.startPosition = startPosition;
        this.damage = damage;
        this.enemy = enemy;
        this.bulletManager = bulletManager;
        this.tower = tower;
    }

    //speed
    public float getSpeed() { return speed; }
    public void setSpeed(float speed) { this.speed = speed; }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
