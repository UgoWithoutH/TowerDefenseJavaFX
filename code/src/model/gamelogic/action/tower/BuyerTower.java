package model.gamelogic.action.tower;

import model.characters.tower.ClassicTower;
import model.characters.tower.Tower;
import model.gamelogic.GameState;
import model.gamelogic.map.Map;
import model.gamelogic.action.Buyer;
import view.map.DrawMap;

public class BuyerTower implements Buyer {
    private GameState game;
    private Map gameMap;
    private DrawMap drawMap;

    public BuyerTower(GameState game, Map gameMap, DrawMap drawMap) {
        this.game = game;
        this.gameMap = gameMap;
        this.drawMap = drawMap;
    }

    @Override
    public void buy(double xCords, double yCords) {
        int xTile = (int) (xCords / 64);
        int yTile = (int) (yCords / 64);

        if (gameMap.nodeOpen(xTile, yTile)) {
            Tower tower = new ClassicTower(xTile, yTile);
            if (game.getCoins() >= Tower.getDefaultSellCost()) {
                game.addTower(tower);
                game.setCoins(game.getCoins() - 50);
                gameMap.setMapNode(((int) (xCords / 64)), ((int) (yCords / 64)), 7);
                drawMap.draw(gameMap);
            }
        }
    }
}