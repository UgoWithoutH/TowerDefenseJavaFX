package game_logic.Map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/** TODO: 10/12/2021
 * mettre dans le loadMap en paramétre l'adresse (url) du fichier)
  */

public class importMap extends Map{

    public importMap(int mapWidth, int mapHeight) {
        super(mapWidth, mapHeight);

        map=loadMap();
        draw();
    }

    /**
     * Fonction qui permet de lire un format de map sur un fichier texte
     * @return int [][] map
     */
    public int[][] loadMap() {

        try {
            InputStream ips = this.getClass().getResourceAsStream("/Map/map.txt");
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            int k = 0;

            // setup
            ligne = br.readLine();
            String[] setup = ligne.split(" ");
            int Y =Integer.parseInt(setup[0]);
            int X = Integer.parseInt(setup[1]);
            String[][] tableau = new String[Y][X];

            int[][] tableauMap = new int[Y][X];
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

            for (int u = 0; u < Y; u++) {

                for (int i = 0; i < X; i++) {
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
