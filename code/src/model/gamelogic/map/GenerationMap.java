package model.gamelogic.map;

/**
 * Classe permettant de générer une Map
 */
public class GenerationMap extends Map{

    /**
     * Génère une map par tableau
     * @param mapWidth  int Largeur du Tableau
     * @param mapHeight int Longueur du Tableau
     */
    public GenerationMap(int mapWidth, int mapHeight) {
        super(mapWidth, mapHeight);
       setMap(generateMapArray());
    }

    /**
     * Génère une map du jeu
     * @return  int [][] Map
     */
    public int[][] generateMapArray(){

        return new int[][]
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
    }
}
