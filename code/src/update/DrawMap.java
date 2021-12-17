package update;

import model.Map.Map;
import javafx.scene.image.*;
import java.nio.ByteBuffer;

public class DrawMap extends ImageView {

    public DrawMap(Map map) {
        draw(map);
    }

    public void draw(Map map) {

        //loads tileset
        Image tileset = loadTileSet(map.TILESET);

        //Pixel reader
        PixelReader tilereader = tileset.getPixelReader();

        //buffer for aRGB 64x64 tiles
        byte[] buffer = new byte[64 * 64 * 4];
        WritablePixelFormat<ByteBuffer> picFormat = WritablePixelFormat.getByteBgraInstance();

        //Pixel writer
        WritableImage paintedMap = new WritableImage(map.RESOLUTION_WIDTH , map.RESOLUTION_HEIGHT);
        PixelWriter tileWriter = paintedMap.getPixelWriter();

        //reads map node than paints the tile
        for(int x = 0; x < map.TILE_LENGTH_X; x++){
            for(int y = 0; y < map.TILE_LENGTH_Y; y++ ){
                //populate each rectangle with tile from PixelReader
                switch(map.map[y][x]){
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

                if(y == map.TILE_LENGTH_Y - 1 & map.OFFSET_Y_FLAG){
                    tileWriter.setPixels(x * 64 , y * 64, 64 , map.OFFSET_Y , picFormat , buffer , 0 , 256);
                }
                else{
                    tileWriter.setPixels(x * 64 , y * 64, 64 , 64 , picFormat , buffer , 0 , 256);
                }
            }
        };
        this.setImage(paintedMap);
    }

    public Image loadTileSet(String tiles){
        return new Image(tiles);
    }
}
