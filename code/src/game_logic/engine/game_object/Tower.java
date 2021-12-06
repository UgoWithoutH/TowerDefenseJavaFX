package game_logic.engine.game_object;

import game_logic.engine.Coordinate;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Tower {
    private static final int BUILD_TIME = 10000;
    private int attackDamage;
    private double attackSpeed;
    private int attackRange;
    private int upgradeTime;
    private int upgradeCost;
    private int sellCost;
    private ArrayList<Projectile> projectileList;
    private Coordinate coords;


    public Tower(int x , int y){
        projectileList = new ArrayList<Projectile>();
        coords = new Coordinate(x , y);
        attackDamage = 5;
        attackSpeed = 1.0;
        attackRange = 200;
        sellCost = 35;
        upgradeCost = 20;
        upgradeTime = 5000;
    }


    public void upgradeTower(){
        attackDamage++;
        attackSpeed = attackSpeed - 0.1;
        attackRange = attackRange + 50;
        upgradeTime += 3000;
        upgradeCost += 20;
    }


    public int getX(){
        return coords.getExactX();
    }

    public int getY(){
        return coords.getExactY();
    }

    public int getAttackRange(){
        return attackRange;
    }

    public int getAttackDamage(){
        return  attackDamage;
    }

    public double getAttackSpeed(){
        return attackSpeed;
    }

    public int getUpgradeCost(){
        return upgradeCost;
    }

    public int getSellCost(){
        return sellCost;
    }

    public int getUpgradeTime(){
        return upgradeTime;
    }

    public ArrayList<Projectile> getProjectileList() {
        return projectileList;
    }

    public Coordinate getCoords(){
        return coords;
    }

    public void setAttackDamage(int attackDamage){
        this.attackDamage = attackDamage;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

}