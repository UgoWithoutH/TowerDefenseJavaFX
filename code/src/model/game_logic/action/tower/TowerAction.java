package model.game_logic.action.tower;

import model.Map.Map;
import model.Map.update.DrawMap;
import model.game_logic.GameState;

public abstract class TowerAction {

    public static void buyTower(double xCords, double yCords, GameState game, Map gameMap, DrawMap drawMap){
        Buy.buy(xCords ,  yCords,  game,  gameMap,  drawMap);
    }

    public void attackTower(){

    }
}
