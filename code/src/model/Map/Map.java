package model.Map;

import model.Coordinate;

import java.util.ArrayList;

/** @Todo
 *     retourner path monstre
 *    acceler la generation de la map/draw
 *    enlever ImageView pour que notre jeux puisse etre lancer en console
 */


public class Map {

    public final String TILESET = "/tileset/tile.png";

    public final int RESOLUTION_WIDTH; //Screen Resolution passed from main
    public final int RESOLUTION_HEIGHT;
    public final int TILE_LENGTH_X;    //Length of tiles
    public final int TILE_LENGTH_Y;
    public final int OFFSET_X;         //Offsets used for printing last tile row
    public final int OFFSET_Y;
    public final boolean OFFSET_X_FLAG;//Used for painting the edge of the tilemap to avoid ArrayOutOfBoundsException
    public final boolean OFFSET_Y_FLAG;

    public int[][] map;

    public Map(int mapWidth , int mapHeight) {
        RESOLUTION_WIDTH = mapWidth;
        RESOLUTION_HEIGHT = mapHeight;

        TILE_LENGTH_X = (int) Math.ceil(mapWidth / 64d);
        TILE_LENGTH_Y = (int) Math.ceil(mapHeight / 64d);

        OFFSET_X = TILE_LENGTH_X * 64 - RESOLUTION_WIDTH;
        OFFSET_Y = TILE_LENGTH_Y * 64 - RESOLUTION_HEIGHT;

        if(OFFSET_X == 0){
            OFFSET_X_FLAG = false;
        }
        else{
            OFFSET_X_FLAG = true;
        }

        if(OFFSET_Y == 0){
            OFFSET_Y_FLAG = false;
        }
        else{
            OFFSET_Y_FLAG = true;
        }


    }

    // TODO: 17/12/2021
    //  ajouter le fait que le point de départ puisse être n'importe ou
    //  et permettre au circuit de revenir sur ses pieds (tourner a gauche)
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
            if (x == TILE_LENGTH_X) {
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
