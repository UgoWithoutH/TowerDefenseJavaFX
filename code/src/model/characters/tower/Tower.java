package model.characters.tower;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Coordinate;
import model.characters.Projectile;
import model.characters.monster.Monster;

public abstract class Tower {
    private static final int BUILD_TIME_SECONDS = 2;
    private int attackDamage;
    private double attackSpeed;
    private int attackRange;
    private int sellCost;
    private ObjectProperty<Projectile> projectile = new SimpleObjectProperty<>();
        public Projectile getProjectile() {return projectile.get();}
        public ObjectProperty<Projectile> projectileProperty() {return projectile;}
        public void setProjectile(Projectile projectile) {this.projectile.set(projectile);}
    private Coordinate coords;
    private boolean attacker = true;
    private boolean build = false;



    public Tower(int x , int y){
        coords = new Coordinate(x , y);
        attackDamage = 5;
        attackSpeed = 1.0;
        attackRange = 200;
        sellCost = 35;
    }

    public int getBuildTimeSeconds() {
        return BUILD_TIME_SECONDS;
    }

    public boolean isBuild() {
        return build;
    }

    public void setBuild(boolean build) {
        this.build = build;
    }

    public int getX() {
        return coords.getExactX();
    }

    public int getY() {
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

    public Coordinate getCoords(){
        return coords;
    }

    public boolean isAttaker() {
        return attacker;
    }

    public void setAttaker(boolean attaker) {
        this.attacker = attaker;
    }

    public void createProjectile(Monster target){
        setProjectile(new Projectile(target , coords.getExactX() , coords.getExactY()));
    }

}
