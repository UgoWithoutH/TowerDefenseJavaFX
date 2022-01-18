package model.characters.tower;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Coordinate;
import model.characters.Character;
import model.characters.Projectile;
import model.characters.monster.Monster;

public abstract class Tower {
    private static int BUILD_TIME_SECONDS = 2;
    private int attackDamage;
    private int attackRange;
    private static  int DEFAULT_SELL_COST = 35;
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
        attackRange = 200;
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

    public static int getDefaultSellCost(){
        return DEFAULT_SELL_COST;
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


    public void createProjectile(Character target){
        setProjectile(new Projectile(target , coords.getExactX() , coords.getExactY()));
    }

}
