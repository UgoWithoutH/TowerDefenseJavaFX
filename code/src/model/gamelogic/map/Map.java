package model.gamelogic.map;

import model.Coordinate;

import java.util.ArrayList;

public class Map {

    private final String tileset = "/tileset/tile.png";
    private int resolutionWidth; //Screen Resolution passed from main
    private int resolutionHeight;
    private final int tileLengthX;    //Length of tiles
    private final int tileLengthY;
    private final int offsetX;         //Offsets used for printing last tile row
    private final int offsetY;
    private final boolean offsetXFlag;//Used for painting the edge of the tilemap to avoid ArrayOutOfBoundsException
    private final boolean offsetYFlag;
    private int[][] map;
    private final String level1 = "/levels/level1.txt";

    public Map(int mapWidth , int mapHeight) {
        resolutionWidth = mapWidth;
        resolutionHeight = mapHeight;

        tileLengthX = (int) Math.ceil(mapWidth / 64d);
        tileLengthY = (int) Math.ceil(mapHeight / 64d);

        offsetX = tileLengthX * 64 - resolutionWidth;
        offsetY = tileLengthY * 64 - resolutionHeight;

        if(offsetX == 0){
            offsetXFlag = false;
        }
        else{
            offsetXFlag = true;
        }

        if(offsetY == 0){
            offsetYFlag = false;
        }
        else{
            offsetYFlag = true;
        }
    }

    public String getTileset() {
        return tileset;
    }

    public int getResolutionWidth() {
        return resolutionWidth;
    }
    public int getResolutionHeight() {
        return resolutionHeight;
    }

    public int getTileLengthX() {
        return tileLengthX;
    }
    public int getTileLengthY() {
        return tileLengthY;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public boolean isOffsetYFlag() {
        return offsetYFlag;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }


    public ArrayList<Coordinate> getPath() {
        ArrayList<Coordinate> pathXY = new ArrayList<Coordinate>();
        boolean scanSwitch = false;
        int previousY = 0;
        int previousX = 0;

        //Cherche la case de départ sur la premiére colonne
        for (int y = 0; !scanSwitch; y++) {
            if (map[y][0] > 0) {
                pathXY.add(new Coordinate(0, y));
                scanSwitch = true;
                previousY = y;
            }
        }


        for (int x = 0; scanSwitch; x++) {
            if (x == tileLengthX) {
                pathXY.add(new Coordinate(x - 1, previousY));
                break;
            }
            if (map[previousY][x] > 2 & map[previousY][x] < 7 & x != previousX) {
                pathXY.add(new Coordinate(x, previousY));
                scanSwitch = false;
                previousX = x;
            }
            for (int y = 0; !scanSwitch; y++) {
                if (map[y][x] > 2 & map[y][x] < 7 & y != previousY) {
                    pathXY.add(new Coordinate(x, y));
                    scanSwitch = true;
                    previousY = y;
                }
            }
        }
        return pathXY;
    }

    /**
     * Set la valeur de la cellule
     * @param xCord
     * @param yCord
     * @param updatedValue
     */
    public void setMapNode(int xCord , int yCord , int updatedValue){
        this.map[yCord][xCord] = updatedValue;
    }

    /**
     * Check si cellule disponible
     * @param xCord
     * @param yCord
     * @return boolean
     */
    public boolean nodeOpen(int xCord , int yCord){
        if(map[yCord][xCord] != 0){
            return false;
        }
        return true;
    }
}
