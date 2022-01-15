package model.characters.monster;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.Coordinate;

import java.util.ArrayList;

/**
 * todo
 *  Nous avons du javafx dans le MODELE !!
 */
public abstract class Monster {
    private static ArrayList<Coordinate> path;
    //Circle view;
    private Coordinate coords;
    private final int radius = 10;
    private int healthPoints;
    private int movementSpeed;
    private int reward;
    private int direction;
    private boolean moveX;
    private boolean isDead;
    private boolean pathFinished;
    private BooleanProperty visible = new SimpleBooleanProperty();
        public boolean isVisible() {return visible.get();}
        public BooleanProperty visibleProperty() {return visible;}
        public void setVisible(boolean visible) {this.visible.set(visible);}

    public Monster(int healthPoints) {
        pathFinished = false;
        moveX = true;
        direction = 1;
        this.healthPoints = healthPoints;
        movementSpeed = 1;
        reward = 2;
        coords = new Coordinate(path.get(0).getExactX(), path.get(0).getExactY());
        setVisible(true);
    }

    public Coordinate getCoords(){return coords;}

    public int getX() {
        return coords.getX();
    }

    public void setX(int x) {
        coords.setX(x);
    }

    public int getY() {
        return coords.getY();
    }

    public void setY(int y) {
        coords.setY(y);
    }

    public int getRadius() {
        return radius;
    }

    public int getReward() {
        return reward;
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

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed){this.movementSpeed = movementSpeed;}

    public void takeDamage(int damage) {
        healthPoints = healthPoints - damage;
        if (healthPoints <= 0) {
            isDead = true;
            pathFinished = false;
        }
    }

    public void updateLocation(int distance) {
        if(pathFinished) return;
        // Déplacement selon l'axe des x
        if (moveX) {
            setX(coords.getX() + distance);
            // Arrivé à un point de changement dans le chemin, changer de direction
            if (coords.getX() == path.get(direction).getExactX()) {
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
            if (coords.getY() < path.get(direction).getExactY()) {
                setY(coords.getY() + distance);
            } else {
                setY(coords.getY() - distance);
            }
            // Atteindre le point de changement, changer de direction
            if (coords.getY() == path.get(direction).getExactY()) {
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
