package game_logic.bullet;

import game_logic.GameObject;
import game_logic.Vector2;
import game_logic.enemy.Enemy;
import game_logic.tower.IUpgradable;
import game_logic.tower.Tower;

public abstract class Bullet extends GameObject implements IUpgradable {
    private Vector2 direction, targetPosition,startPosition;
    private float speed;
    private int damage;
    private Enemy enemy;
    private Tower tower;

    public Bullet(String name,Vector2 startPosition,float speed, int damage, Tower tower) {
        super(name,startPosition);
        this.startPosition = startPosition;
        this.speed = speed;
        this.damage = damage;
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

    //tower
    public Tower getTower() { return tower; }
}
