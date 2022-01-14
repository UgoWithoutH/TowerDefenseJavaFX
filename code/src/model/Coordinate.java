package model;

public class Coordinate {
    private int x;
    private int y;


    public Coordinate(int x , int y){
        this.x = x;
        this.y = y;
    }

    public Coordinate(double x , double y){
        this.x = (int)(x / 64);
        this.y = (int) (y / 64);
    }

    public int getTileX() {
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
    }

    public int getExactX() {
        return x * 64 + 32;
    }

    public int getExactY() {
        return y * 64 + 32;
    }

    public void setExactX(int x) {
        this.x = x;
    }

    public void setExactY(int y) {
        this.y = y;
    }

    public boolean equals(Coordinate obj) {
        if (this.x == obj.x && this.y == obj.y) {
            return true;
        }
        return false;
    }
}
