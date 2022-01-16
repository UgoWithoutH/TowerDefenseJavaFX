package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Coordinate {

    //private int x;
    //private int y;
    private IntegerProperty x = new SimpleIntegerProperty();
        public int getX() {return x.get();}
        public IntegerProperty xProperty() {return x;}
        public void setX(int x) {this.x.set(x);}
    private IntegerProperty y = new SimpleIntegerProperty();
        public int getY() {return y.get();}
        public IntegerProperty yProperty() {return y;}
        public void setY(int y) {this.y.set(y);}

    public Coordinate(int x , int y){
        setX(x);
        setY(y);
    }

    public Coordinate(double x , double y){
        var testX = (int) (getX()/64);
        var testY = (int) (getY()/64);

        setX(testX);
        setY(testY);
    }

    /*public int getTileX() {
        return x;
    }

    public int getTileY() {
        return y;
    }

    public void setTileX(int x) {
        this.x = x;
    }

    public void setTileY(int y) {
        this.y = y;
    }*/

    public int getExactX() {
        return getX() * 64 + 32;
    }

    public int getExactY() {
        return getY() * 64 + 32;
    }

    /*public void setExactX(int x) {
        this.x = x;
    }

    public void setExactY(int y) {
        this.y = y;
    }*/

    public boolean equals(Coordinate obj) {
        if (this.x == obj.x && this.y == obj.y) {
            return true;
        }
        return false;
    }
}
