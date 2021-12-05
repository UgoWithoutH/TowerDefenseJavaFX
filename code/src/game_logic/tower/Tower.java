package game_logic.tower;

import game_logic.GameObject;
import game_logic.Vector2;
import game_logic.enemy.Enemy;
import jdk.jshell.spi.ExecutionControl;

public abstract class Tower extends GameObject {
    //valeurs par défault

    private int currentLevel = 1;
    private boolean upgradable = true;
    private TowerState towerState = TowerState.BUILD; //enum
    private int range; //range en pixel
    private BulletType bulletType;
    private int attackRate;
    private int cost;

    public Tower(String name, Vector2 position, int range, BulletType bulletType, int attackRate, int cost){
        super(name,position);//appel constructeur de GameObject
        this.range = range;
        this.attackRate = attackRate;
        this.cost = cost;
    }
    //level
    public int getCurrentlevel() { return currentLevel; }

    //Upgradable
    public boolean isUpgradable(){
        return upgradable;
    }

    public void setUpgradable(boolean value){
        upgradable = value;
    }

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

    public void attack(Enemy m) throws ExecutionControl.NotImplementedException {
            throw new ExecutionControl.NotImplementedException("");
    }

    public void upgrade() throws Exception {
        if (isUpgradable())
            currentLevel++;
        else
            throw new Exception("");
    }
}
