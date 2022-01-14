package model.game_logic.action.tower;

import model.game_logic.GameState;
import model.game_logic.action.Map.Map;
import model.game_logic.action.Map.update.DrawMap;

public abstract class TowerAction {

    public static void buyTower(double xCords, double yCords, GameState game, Map gameMap, DrawMap drawMap) {
        Buy.buy(xCords, yCords, game, gameMap, drawMap);
    }

    public static void attackTower(GameState game) {
        Attacker.attack(game);
    }

}
