package model.gamelogic.action.tower;

import model.characters.tower.ClassicTower;
import model.characters.tower.Tower;
import model.gamelogic.GameState;
import model.gamelogic.map.Map;
import model.gamelogic.action.IBuyer;
import view.map.DrawMap;

public class BuyerTower implements IBuyer {
    private GameState game;
    private Map gameMap;
    private DrawMap drawMap;

    public BuyerTower(GameState game, Map gameMap, DrawMap drawMap) {
        this.game = game;
        this.gameMap = gameMap;
        this.drawMap = drawMap;
    }

    /**
     * Achat et placement d'une Tower en fonction de la fenêtre
     * @param xCords    double Position X sur la Fenetre
     * @param yCords    double Position Y sur la Fenetre
     */
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
