package model.characters.tower;



import model.Coordinate;
import javafx.scene.paint.Color;
import model.characters.Projectile;
import model.characters.monster.Monster;

import java.util.ArrayList;


public abstract class Tower {
    private static final int BUILD_TIME_SECONDS = 2;
    private int attackDamage;
    private double attackSpeed;
    private int attackRange;
    private int upgradeTime;
    private int upgradeCost;
    private int sellCost;
    private ArrayList<Projectile> projectileList; //TO DO faire une liste observable
    private Coordinate coords;
    private boolean attacker = true;
    private boolean buildable = false;



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

    public int getBuildTimeSeconds(){
        return BUILD_TIME_SECONDS;
    }

    public boolean isBuildable() {
        return buildable;
    }

    public void setBuildable(boolean buildable) {
        this.buildable = buildable;
    }

    public void upgradeTower(){
        attackDamage++;
        attackSpeed = attackSpeed - 0.1;
        attackRange = attackRange + 50;
        upgradeTime += 3000;
        upgradeCost += 20;
    }

    public void createProjectile(Monster target){
        projectileList.add(new Projectile(target , coords.getExactX() , coords.getExactY() , Color.BLACK));
    }


    public int getX(){
        return coords.getExactX();
    }

    public int getY(){
        return coords.getExactY();
    }

    public int getTileX(){
        return coords.getTileX();
    }

    public int getTileY(){
        return coords.getTileY();
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

    public boolean isAttaker() {
        return attacker;
    }

    public void setAttaker(boolean attaker) {
        this.attacker = attaker;
    }

}
