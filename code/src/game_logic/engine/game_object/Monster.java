package game_logic.engine.game_object;

import game_logic.engine.Coordinate;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Monster {
    private static ArrayList<Coordinate> path;
    private Circle view;
    private final int radius = 10;
    private int healthPoints;
    private int movementSpeed;
    private int reward;
    private boolean moveX;
    private boolean isDead;
    private boolean pathFinished;

    public Monster(int healthPoints){
        pathFinished = false;
        moveX = true;
        this.healthPoints = healthPoints;
        movementSpeed = 1;
        reward = 2;
        view = new Circle(path.get(0).getExactX() , path.get(0).getExactY() , radius);
        view.setFill(Color.RED);
    }


    public int getX(){
        return ((int)view.getCenterX());
    }
    public int getY(){
        return ((int)view.getCenterY());
    }
    public int getReward(){
        return reward;
    }
    public Circle getView(){
        return view;
    }
    public boolean isDead(){
        return isDead;
    }

    public boolean isPathFinished(){
        return pathFinished;
    }

    public static void setPath(ArrayList<Coordinate> pathSet){
        path = pathSet;
    }

    public void takeDamage(int damage){
        healthPoints = healthPoints - damage;
        if (healthPoints <= 0){
            isDead = true;
            pathFinished = false;
        }
    }


}
