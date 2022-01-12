package model.game_logic.action.tower;

import model.Map.Map;
import model.Map.update.DrawMap;
import model.characters.tower.ClassicTower;
import model.characters.tower.Tower;
import model.game_logic.GameState;

public class Buy {
    public static void buy(double xCords , double yCords, GameState game, Map gameMap, DrawMap drawMap){
        int xTile = (int)(xCords / 64);
        int yTile = (int)(yCords / 64);

        if(gameMap.nodeOpen(xTile,yTile)){
            Tower tower = new ClassicTower(xTile, yTile);
            if(game.getCoins() >= tower.getSellCost()) {
                game.addTower(tower);
                game.setCoins(game.getCoins() - 50);
                gameMap.setMapNode(((int) (xCords / 64)), ((int) (yCords / 64)), 7);
                drawMap.draw(gameMap);
            }
        }
    }
}
