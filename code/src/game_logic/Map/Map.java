package game_logic.Map;

import game_logic.Update;
import javafx.scene.image.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

/** @Todo
*    retourner path monstre
 *    acceler la generation de la map/draw
 */

public class Map extends ImageView implements Update {

    private final static String TILESET = "/tileset/tile.png";

    private final int RESOLUTION_WIDTH; //Screen Resolution passed from main
    private final int RESOLUTION_HEIGHT;
    private final int TILE_LENGTH_X;    //Length of tiles
    private final int TILE_LENGTH_Y;
    private final int OFFSET_X;         //Offsets used for printing last tile row
    private final int OFFSET_Y;
    private final boolean OFFSET_X_FLAG;//Used for painting the edge of the tilemap to avoid ArrayOutOfBoundsException
    private final boolean OFFSET_Y_FLAG;

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

    public void draw(){

        //loads tileset
        Image tileset = loadTileSet();

        //Pixel reader
        PixelReader tilereader = tileset.getPixelReader();

        //buffer for aRGB 64x64 tiles
        byte[] buffer = new byte[64 * 64 * 4];
        WritablePixelFormat<ByteBuffer> picFormat = WritablePixelFormat.getByteBgraInstance();

        //Pixel writer
        WritableImage paintedMap = new WritableImage(RESOLUTION_WIDTH , RESOLUTION_HEIGHT);
        PixelWriter tileWriter = paintedMap.getPixelWriter();

        //reads map node than paints the tile
        for(int x = 0; x < TILE_LENGTH_X; x++){
            for(int y = 0; y < TILE_LENGTH_Y; y++ ){
                //populate each rectangle with tile from PixelReader
                switch(map[y][x]){
                    case 0: //paint grass(OPEN NODE)
                        tilereader.getPixels(384 , 64 , 64 , 64 , picFormat , buffer , 0 , 256);
                        break;
                    case 1: //paint horizontal path
                        tilereader.getPixels(384 , 192 , 64 , 64 , picFormat , buffer , 0 , 256);
                        break;
                    case 2: //paint vertical path
                        tilereader.getPixels(448 , 128 , 64 , 64 , picFormat , buffer , 0 , 256);
                        break;
                    case 3: //paint corner EAST TO NORTH
                        tilereader.getPixels(256 , 192 , 64 , 64 , picFormat , buffer , 0 , 256);
                        break;
                    case 4: //paint corner SOUTH TO EAST
                        tilereader.getPixels(192 , 192 , 64 , 64 , picFormat , buffer , 0 , 256);
                        break;
                    case 5: //paint corner NORTH TO EAST
                        tilereader.getPixels(192 , 128 , 64 , 64 , picFormat , buffer , 0 , 256);
                        break;
                    case 6: //paint corner EAST TO SOUTH
                        tilereader.getPixels(256 , 128 , 64 , 64 , picFormat , buffer , 0 , 256);
                        break;
                    case 7: //paint grass and tower
                        tilereader.getPixels(384 , 512 , 64 , 64 , picFormat , buffer , 0 , 256);
                        break;
                }

                if(y == TILE_LENGTH_Y - 1 & OFFSET_Y_FLAG){
                    tileWriter.setPixels(x * 64 , y * 64, 64 , OFFSET_Y , picFormat , buffer , 0 , 256);
                }
                else{
                    tileWriter.setPixels(x * 64 , y * 64, 64 , 64 , picFormat , buffer , 0 , 256);
                }
            }
        };
        this.setImage(paintedMap);
    }


    /**
     * Charge image
     *
     * */
    private Image loadTileSet(){
        return new Image(TILESET);
    }


    //sets the map node for the given coordinates to input value than repaints adjustment
    public void setMapNode(int xCord , int yCord , int updatedValue){
        map[yCord][xCord] = updatedValue;
        this.draw();
    }

    //checks to see if the node is open
    public boolean nodeOpen(int xCord , int yCord){
        if(map[yCord][xCord] != 0){
            return false;
        }
        return true;
    }
}
