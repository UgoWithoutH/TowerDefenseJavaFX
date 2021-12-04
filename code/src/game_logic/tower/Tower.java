package game_logic.tower;

import game_logic.GameObject;
import game_logic.Vector2;
import game_logic.enemy.Enemy;
import jdk.jshell.spi.ExecutionControl;

public abstract class Tower extends GameObject {
    //valeurs par défault
    public static final int DEFAULT_LEVEL = 1;
    public static final TowerState DEFAULT_TOWERSTATE = TowerState.BUILD;

    private int level;
    private TowerState towerState; //enum
    private int range; //range en pixel
    private BulletType bulletType; //je pense que c'est une bonne idée que ce type soit le type de la bullet
    private int attackRate;
    private int cost;

    public Tower(String name, Vector2 position, int level, TowerState towerState, int range, BulletType bulletType, int attackRate, int cost){
        super(name,position);//appel constructeur de GameObject
        this.level = level;
        this.towerState = towerState;
        this.range = range;
        this.attackRate = attackRate;
        this.cost = cost;
    }
    //level
    public int getLevel() { return level; }
    private void setLevel(int level) { this.level = level; }

    //tower state
    public TowerState getTowerState() { return towerState; }
    private void setTowerState(TowerState towerState) { this.towerState = towerState; }

    //range
    public int getRange() { return range; }
    private void setRange(int range) { this.range = range; }

    //damage type
    public BulletType getDamageType() { return bulletType; }
    private void setDamageType(BulletType damageType) { this.bulletType = damageType; }

    //attack rate
    public int getAttackRate() { return attackRate; }
    private void setAttackRate(int attackRate) { this.attackRate = attackRate; }

    //cost
    public int getCost() { return cost; }
    private void setCost(int cost) { this.cost = cost; }

        public void attack(Enemy m){
            new ExecutionControl.NotImplementedException("");
        }
}
