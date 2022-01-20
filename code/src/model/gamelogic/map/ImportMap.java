package model.gamelogic.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe permettant de générer une Map
 */
public class ImportMap extends Map{

    public ImportMap(int mapWidth, int mapHeight) {
        super(mapWidth, mapHeight);
        setMap(loadMap());
    }

    /**
     * Fonction qui permet de lire un format de map sur un fichier texte
     * @return int [][] map
     */
    public int[][] loadMap() {

        try {
            InputStream ips = this.getClass().getResourceAsStream("/map/map.txt");
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


    /**
     * Fonction qui retourne la List de fichier conforme (extension uniquement)
     * @return List nom des Map disponible
     */
    public List listeMap() {

        List adresse= new ArrayList();
        File dir = new File("code/ressources/Map");
        File[] liste = dir.listFiles();
        for (File item : liste) {
            String[] res = item.getName().split("\\.");
            System.out.println(res[1]);
            if (item.isFile() && Objects.equals(res[1], "map")) {
                adresse.add(item);
            }
        }
        return adresse;
    }
}
