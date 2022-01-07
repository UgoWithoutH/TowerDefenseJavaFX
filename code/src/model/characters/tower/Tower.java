package model.characters.tower;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private int sellCost;
    private ObservableList<Projectile> projectileList;
    private Coordinate coords;
    private boolean attacker = true;
    private boolean buildable = false;



    public Tower(int x , int y){
        projectileList = FXCollections.observableArrayList();
        coords = new Coordinate(x , y);
        attackDamage = 5;
        attackSpeed = 1.0;
        attackRange = 200;
        sellCost = 35;
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

    public int getAttackRange(){
        return attackRange;
    }

    public int getAttackDamage(){
        return  attackDamage;
    }

    public int getSellCost(){
        return sellCost;
    }

    public ObservableList<Projectile> getProjectileList() {
        return projectileList;
    }

    public Coordinate getCoords(){
        return coords;
    }

    public boolean isAttaker() {
        return attacker;
    }

    public void setAttaker(boolean attaker) {
        this.attacker = attaker;
    }

}
