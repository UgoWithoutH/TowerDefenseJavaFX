package model.characters;

import model.Coordinate;

import java.util.ArrayList;

public abstract class Character {
    private static ArrayList<Coordinate> path;
    private Coordinate coords;
    private int healthPoints;
    private int reward;
    private int direction;
    private boolean moveX;
    private boolean dead;
    private boolean pathFinished;
    private int movementSpeed;

    public Character(int healthPoints, int movementSpeed) {
        pathFinished = false;
        moveX = true;
        direction = 1;
        this.movementSpeed = movementSpeed;
        this.healthPoints = healthPoints;
        reward = 2;
        coords = new Coordinate(path.get(0).getExactX(), path.get(0).getExactY());
    }

    public int getDirection() {return direction;}
    public void setDirection(int direction) {this.direction = direction;}

    public boolean isMoveX() {return moveX;}
    public void setMoveX(boolean moveX) {this.moveX = moveX;}

    public int getHealthPoints() {return healthPoints;}
    public void setHealthPoints(int healthPoints) {this.healthPoints = healthPoints;}

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

    public int getReward() {
        return reward;
    }

    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean value){dead = value;}

    public boolean isPathFinished() {
        return pathFinished;
    }
    public void setPathFinished(boolean pathFinished) {this.pathFinished = pathFinished;}

    public static void setPath(ArrayList<Coordinate> pathSet) {path = pathSet;}
    public ArrayList<Coordinate> getPath(){return path;}

    public int getMovementSpeed() {return movementSpeed;}
    public void setMovementSpeed(int movementSpeed){this.movementSpeed = movementSpeed;}

    public void updateLocation() {
        if(pathFinished) return;
        // Déplacement selon l'axe des x
        if (moveX) {
            setX(coords.getX() + movementSpeed);
            // Arrivé à un point de changement dans le chemin, changer de direction
            if (coords.getX() == path.get(direction).getExactX()) {
                moveX = false;
                direction++;
                // Traversée de tous les points de changement, fin du chemin
                if (direction == path.size()) {
                    pathFinished = true;
                    dead = true;
                }
            }
        }
        // Déplacement selon l'axe des y
        else {
            if (coords.getY() < path.get(direction).getExactY()) {
                setY(coords.getY() + movementSpeed);
            } else {
                setY(coords.getY() - movementSpeed);
            }
            // Atteindre le point de changement, changer de direction
            if (coords.getY() == path.get(direction).getExactY()) {
                moveX = true;
                direction++;
                if (direction == path.size()) {
                    pathFinished = true;
                    dead = true;
                }
            }
        }
    }
}
