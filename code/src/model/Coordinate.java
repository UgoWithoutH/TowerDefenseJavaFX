package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Coordonnées
 */
public class Coordinate {

    private IntegerProperty x = new SimpleIntegerProperty();
        public int getX() {return x.get();}
        public IntegerProperty xProperty() {return x;}
        public void setX(int x) {this.x.set(x);}
    private IntegerProperty y = new SimpleIntegerProperty();
        public int getY() {return y.get();}
        public IntegerProperty yProperty() {return y;}
        public void setY(int y) {this.y.set(y);}

    /**
     * Coordonée sur la map
     * @param x int Position X
     * @param y int Position Y
     */
    public Coordinate(int x , int y){
        setX(x);
        setY(y);
    }

    /**
     * Accède a la position X exacte sur la fenêtre
     * @return int Position X
     */
    public int getExactX() {
        return getX() * 64 + 32;
    }

    /**
     * Accède a la position Y exacte sur la fenêtre
     * @return  int Position Y
     */
    public int getExactY() {
        return getY() * 64 + 32;
    }

    public boolean equals(Coordinate obj) {
        return this.x == obj.x && this.y == obj.y;
    }
}
