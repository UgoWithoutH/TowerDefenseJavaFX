package model.characters;

public class Projectile{
    private final Character target;
    private final int startX;
    private final int startY;


    public Projectile(Character target, int towerX, int towerY){
        this.target = target;
        startX = towerX;
        startY = towerY;
    }

    public Character getTarget(){
        return target;
    }

    public int getEndX(){
        return target.getX();
    }

    public int getEndY(){
        return target.getY();
    }

    public int getStartX(){
        return startX;
    }

    public int getStartY(){
        return startY;
    }

}

