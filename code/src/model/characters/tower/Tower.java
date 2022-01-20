package model.characters.tower;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Coordinate;
import model.characters.Character;
import model.characters.Projectile;

/**
 * Tour
 */
public abstract class Tower {
    private static final int BUILD_TIME_SECONDS = 2; //Temps de construction
    private static final int DEFAULT_SELL_COST = 25; //Prix de construction
    private int attackDamage;
    private int attackRange;
    private ObjectProperty<Projectile> projectile = new SimpleObjectProperty<>();
        public Projectile getProjectile() {return projectile.get();}
        public ObjectProperty<Projectile> projectileProperty() {return projectile;}
        public void setProjectile(Projectile projectile) {this.projectile.set(projectile);}
    private Coordinate coordinate;
    private boolean attacker = true;
    private boolean build = false;


    /**
     * Création d'une Tower à une position X et Y sur la map
     * @param x int position X sur la map
     * @param y int position Y sur la map
     */
    public Tower(int x , int y){
        coordinate = new Coordinate(x , y);
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
        return coordinate.getExactX();
    }
    public int getY() {
        return coordinate.getExactY();
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

    public Coordinate getCoordinate(){
        return coordinate;
    }

    public boolean isAttacker() {
        return attacker;
    }
    public void setAttacker(boolean attacker) {
        this.attacker = attacker;
    }

    /**
     * Créé un projetctile et cible un Character
     * @param target Character ciblé
     */
    public void createProjectile(Character target){
        setProjectile(new Projectile(target , coordinate.getExactX() , coordinate.getExactY()));
    }

}
