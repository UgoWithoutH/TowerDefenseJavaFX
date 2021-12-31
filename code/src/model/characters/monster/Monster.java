package model.characters.monster;

import model.Coordinate;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public abstract class Monster {
    private static ArrayList<Coordinate> path;
    Circle view;
    private final int radius = 10;
    private int healthPoints;
    int movementSpeed;
    private int reward;
    private int direction;
    private boolean moveX;
    private boolean isDead;
    private boolean pathFinished;

    public Monster(int healthPoints) {
        pathFinished = false;
        moveX = true;
        direction = 1;
        this.healthPoints = healthPoints;
        movementSpeed = 1;
        reward = 2;
        view = new Circle(path.get(0).getExactX(), path.get(0).getExactY(), radius);
        view.setFill(Color.RED);
    }


    public int getX() {
        return ((int) view.getCenterX());
    }

    public int getY() {
        return ((int) view.getCenterY());
    }

    public int getReward() {
        return reward;
    }

    public Circle getView() {
        return view;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isPathFinished() {
        return pathFinished;
    }

    public static void setPath(ArrayList<Coordinate> pathSet) {
        path = pathSet;
    }

    public void takeDamage(int damage) {
        healthPoints = healthPoints - damage;
        if (healthPoints <= 0) {
            isDead = true;
            pathFinished = false;
        }
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void updateLocation(int distance) {

        // Déplacement selon l'axe des x
        if (moveX) {
            view.setCenterX(view.getCenterX() + distance);
            // Arrivé à un point de changement dans le chemin, changer de direction
            if (view.getCenterX() == path.get(direction).getExactX()) {
                moveX = false;
                direction++;
                // Traversée de tous les points de changement, fin du chemin
                if (direction == path.size()) {
                    pathFinished = true;
                    isDead = true;
                }
            }
        }
        // Déplacement selon l'axe des y
        else {
            if (view.getCenterY() < path.get(direction).getExactY()) {
                view.setCenterY(view.getCenterY() + distance);
            } else {
                view.setCenterY(view.getCenterY() - distance);
            }
            // Atteindre le point de changement, changer de direction
            if (view.getCenterY() == path.get(direction).getExactY()) {
                moveX = true;
                direction++;
                if (direction == path.size()) {
                    pathFinished = true;
                    isDead = true;
                }
            }
        }
    }
}
