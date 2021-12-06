package game_logic.engine.game_object;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Projectile extends Circle {
    private final int startX;
    private final int startY;


    Projectile(int towerX , int towerY , Color color){
        super(towerX , towerY , 5 , color);
        startX = towerX;
        startY = towerY;
    }

    public int getStartX(){
        return startX;
    }

    public int getStartY(){
        return startY;
    }



}
