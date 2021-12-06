package game_logic;

import javafx.scene.image.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/* @Todo
*    importation map
*    generation map
*    dessinner map
*    retourner path monstre
 */

public class Map {

    private final static String TILESET = "../ressources/tileset/tile.png";

    private final int RESOLUTION_WIDTH; //Screen Resolution passed from main
    private final int RESOLUTION_HEIGHT;
    private int[][] map;

    public Map(int mapWidth , int mapHeight) {
        RESOLUTION_WIDTH = mapWidth;
        RESOLUTION_HEIGHT = mapHeight;

        map = generateMapArray();             //generation map
        //repaint();                            //dessiner la map
    }

    private int[][] generateMapArray(){
        int[][] map;

        map = new int[][]
                {
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 5 , 1 , 1 , 1 , 1 , 1 , 1 , 1 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 2 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 5 , 1 , 1 , 6 , 0 , 0 , 2 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 2 , 0 , 0 , 2 , 0 , 0 , 2 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 2 , 0 , 0 , 2 , 0 , 0 , 2 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 2 , 0 , 0 , 2 , 0 , 0 , 2 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {1 , 1 , 1 , 1 , 1 , 1 , 3 , 0 , 0 , 4 , 1 , 1 , 3 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
                        {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 }
                };
        return map;
    }

    //Charge image
    private Image loadTileSet(){
        return new Image(TILESET);
    }

    //a deplacer dans un package import
    public static int[][] loadMap() {

        try {
            InputStream ips = new FileInputStream("map/tileMap.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            int k = 0;

            // setup
            ligne = br.readLine();
            String[] setup = ligne.split(" ");
            String[][] tableau = new String[Integer.parseInt(setup[0])][Integer.parseInt(setup[1])];
            // final
            int[][] tableauMap = new int[Integer.parseInt(setup[0])][Integer.parseInt(setup[1])];
            while ((ligne = br.readLine()) != null) {

                if (ligne.startsWith("#")) {
                    continue;
                }

                String[] resultat = ligne.split(" ");
                for (int i = 0; i < resultat.length; i++) {
                    tableau[k][i] = resultat[i];
                }
                k++;
            }

            for (int u = 0; u < tableauMap.length; u++) {

                for (int i = 0; i < tableau.length; i++) {
                    tableauMap[u][i] = Integer.parseInt(tableau[u][i]);
                }
            }
            br.close();

            return tableauMap;

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
