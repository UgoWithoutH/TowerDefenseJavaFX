package model.characters;

import model.Coordinate;

import java.util.ArrayList;

public abstract class Character {
    private static ArrayList<Coordinate> path;
    private Coordinate coordinate;
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
        coordinate = new Coordinate(path.get(0).getExactX(), path.get(0).getExactY());
    }

    public int getDirection() {return direction;}
    public void setDirection(int direction) {this.direction = direction;}

    public boolean isMoveX() {return moveX;}
    public void setMoveX(boolean moveX) {this.moveX = moveX;}

    public int getHealthPoints() {return healthPoints;}
    public void setHealthPoints(int healthPoints) {this.healthPoints = healthPoints;}

    public Coordinate getCoordinate(){return coordinate;}

    public int getX() {
        return coordinate.getX();
    }
    public void setX(int x) {
        coordinate.setX(x);
    }

    public int getY() {
        return coordinate.getY();
    }
    public void setY(int y) {
        coordinate.setY(y);
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

    /**
     * Update Location of Characters (Monsters) by path
     */
    public void updateLocation() {
        if(pathFinished) return;
        // Movement along the x axis
        if (moveX) {
            setX(coordinate.getX() + movementSpeed);
            // Arrived at a point of change in the path, change direction
            if (coordinate.getX() == path.get(direction).getExactX()) {
                moveX = false;
                direction++;
                // Crossing all changepoints, end of the path
                if (direction == path.size()) {
                    pathFinished = true;
                    dead = true;
                }
            }
        }
        // Movement along the y axis
        else {
            if (coordinate.getY() < path.get(direction).getExactY()) {
                setY(coordinate.getY() + movementSpeed);
            } else {
                setY(coordinate.getY() - movementSpeed);
            }
            // Reach the change point, change direction
            if (coordinate.getY() == path.get(direction).getExactY()) {
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
