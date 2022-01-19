package view.map;

import javafx.scene.image.*;
import model.gamelogic.map.Map;

import java.nio.ByteBuffer;

public class DrawMap extends ImageView {

    public DrawMap(Map map) {
        draw(map);
    }

    /**
     * Dessine la map en fonction des valeurs de Map (int [][])
     * @param map   Map
     */
    public void draw(Map map) {

        //Charge tileset
        Image tileset = loadTileSet(map.getTileset());

        //Pixel reader
        PixelReader tilereader = tileset.getPixelReader();

        //buffer pour aRGB 64x64 Tiles
        byte[] buffer = new byte[64 * 64 * 4];
        WritablePixelFormat<ByteBuffer> picFormat = WritablePixelFormat.getByteBgraInstance();

        //Pixel writer
        WritableImage paintedMap = new WritableImage(map.getResolutionWidth() , map.getResolutionHeight());
        PixelWriter tileWriter = paintedMap.getPixelWriter();

        //Lis map node et dessinne Tile
        for(int x = 0; x < map.getTileLengthX(); x++){
            for(int y = 0; y < map.getTileLengthY(); y++ ){
                switch (map.getMap()[y][x]) {
                    case 0 -> //paint grass(OPEN NODE)
                            tilereader.getPixels(384, 64, 64, 64, picFormat, buffer, 0, 256);
                    case 1 -> //paint horizontal path
                            tilereader.getPixels(384, 192, 64, 64, picFormat, buffer, 0, 256);
                    case 2 -> //paint vertical path
                            tilereader.getPixels(448, 128, 64, 64, picFormat, buffer, 0, 256);
                    case 3 -> //paint corner EAST TO NORTH
                            tilereader.getPixels(256, 192, 64, 64, picFormat, buffer, 0, 256);
                    case 4 -> //paint corner SOUTH TO EAST
                            tilereader.getPixels(192, 192, 64, 64, picFormat, buffer, 0, 256);
                    case 5 -> //paint corner NORTH TO EAST
                            tilereader.getPixels(192, 128, 64, 64, picFormat, buffer, 0, 256);
                    case 6 -> //paint corner EAST TO SOUTH
                            tilereader.getPixels(256, 128, 64, 64, picFormat, buffer, 0, 256);
                    case 7 -> //paint grass and tower
                            tilereader.getPixels(384, 512, 64, 64, picFormat, buffer, 0, 256);
                }
                if(y == map.getTileLengthY() - 1 & map.isOffsetYFlag()){
                    tileWriter.setPixels(x * 64 , y * 64, 64 , map.getOffsetY() , picFormat , buffer , 0 , 256);
                }
                else{
                    tileWriter.setPixels(x * 64 , y * 64, 64 , 64 , picFormat , buffer , 0 , 256);
                }
            }
        }
        this.setImage(paintedMap);
    }

    public Image loadTileSet(String tiles){
        return new Image(tiles);
    }

}
