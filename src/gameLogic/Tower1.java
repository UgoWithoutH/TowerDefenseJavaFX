package gameLogic;

public class Tower1 extends Tower{

    public  Vector2 position;
    public int level;
    public String towerState; //enum de State (stunt, repair, build)
    public int range;
    public String damageType; //enum de Type de damage a creer
    public int attackRate;
    public int cost;

    public Tower1(Vector2 position, int level, String towerState, int range, String damageType, int attackRate, int cost) {
        this.position = position;
        this.level = level;
        this.towerState = towerState;
        this.range = range;
        this.damageType = damageType;
        this.attackRate = attackRate;
        this.cost = cost;
    }

    public int calculateDamage(){
        return 50;
    }
}

