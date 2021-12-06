package game_logic.engine.game_object;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Projectile extends Circle {
    private Monster target;
    private final int startX;
    private final int startY;


    Projectile(Monster target , int towerX , int towerY , Color color){
        super(towerX , towerY , 5 , color);
        this.target = target;
        startX = towerX;
        startY = towerY;
    }

    public Monster getTarget(){
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
