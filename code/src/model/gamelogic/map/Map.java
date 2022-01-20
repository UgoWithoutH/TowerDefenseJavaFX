package model.gamelogic.map;

import model.Coordinate;

import java.util.ArrayList;

/**
 * carte
 */
public class Map {

    private final String tileset = "/tileset/tile.png";
    private final int resolutionWidth;
    private final int resolutionHeight;
    private final int tileLengthX;
    private final int tileLengthY;
    private final int offsetX;
    private final int offsetY;
    private final boolean offsetXFlag;
    private final boolean offsetYFlag;
    private int[][] map;

    /**
     * Creation d'une Map a partir d'un int[][]
     * @param mapWidth  int Largeur du Tableau
     * @param mapHeight int Longueur du Tableau
     */
    public Map(int mapWidth , int mapHeight) {
        resolutionWidth = mapWidth;
        resolutionHeight = mapHeight;

        tileLengthX = (int) Math.ceil(mapWidth / 64d);
        tileLengthY = (int) Math.ceil(mapHeight / 64d);

        offsetX = tileLengthX * 64 - resolutionWidth;
        offsetY = tileLengthY * 64 - resolutionHeight;

        offsetXFlag = offsetX != 0;

        offsetYFlag = offsetY != 0;
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

    /**
     * Définit la path a prendre pour tout les Characters
     * @return  ArrayList<Coordinate>
     */
    public ArrayList<Coordinate> getPath() {
        ArrayList<Coordinate> pathXY = new ArrayList<>();
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
     * @param xCord int Position X
     * @param yCord int Position Y
     * @param updatedValue  int Valeur a remplacer
     */
    public void setMapNode(int xCord , int yCord , int updatedValue){
        this.map[yCord][xCord] = updatedValue;
    }

    /**
     * Check si cellule disponible
     * @param xCord int Position X
     * @param yCord int Position Y
     * @return boolean Si disponible
     */
    public boolean nodeOpen(int xCord , int yCord){
        return map[yCord][xCord] == 0;
    }
}
