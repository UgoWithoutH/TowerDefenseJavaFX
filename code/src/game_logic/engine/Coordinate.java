package game_logic.engine;

public class Coordinate {
    private int x;
    private int y;


    public Coordinate(int x , int y){
        this.x = x;
        this.y = y;
    }

    public int getTileX(){
        return x;
    }
    public int getTileY(){
        return y;
    }

    public int getExactX(){
        return x;
    }

    public int getExactY(){
        return y;
    }


    public boolean equals(Coordinate obj) {
        if(this.x == obj.x && this.y == obj.y){
            return true;
        }
        return false;
    }
}
