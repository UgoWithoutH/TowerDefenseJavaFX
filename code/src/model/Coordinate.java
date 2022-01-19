package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Coordinate {

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
        var testX = getX()/64;
        var testY = getY()/64;

        setX(testX);
        setY(testY);
    }

    public int getExactX() {
        return getX() * 64 + 32;
    }

    public int getExactY() {
        return getY() * 64 + 32;
    }

    public boolean equals(Coordinate obj) {
        return this.x == obj.x && this.y == obj.y;
    }
}
