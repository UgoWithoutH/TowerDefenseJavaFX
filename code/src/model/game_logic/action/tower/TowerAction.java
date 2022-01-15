package model.game_logic.action.tower;

import model.characters.monster.Monster;
import model.characters.tower.ClassicTower;
import model.characters.tower.Tower;
import model.game_logic.GameState;
import model.game_logic.Map.Map;
import view.map.DrawMap;

import java.util.Iterator;

public abstract class TowerAction {

    public static void buyTower(double xCords, double yCords, GameState game, Map gameMap, DrawMap drawMap) {
        int xTile = (int) (xCords / 64);
        int yTile = (int) (yCords / 64);

        if (gameMap.nodeOpen(xTile, yTile)) {
            Tower tower = new ClassicTower(xTile, yTile);
            if (game.getCoins() >= tower.getSellCost()) {
                game.addTower(tower);
                game.setCoins(game.getCoins() - 50);
                gameMap.setMapNode(((int) (xCords / 64)), ((int) (yCords / 64)), 7);
                drawMap.draw(gameMap);
            }
        }
    }

    public static void attackTower(GameState game) {
        Monster target;
        Waiting attackService;

        if(game.isGameOver()) return;

        for (Tower tower : game.getPlayerTowers()) {
            if (tower.isAttaker()) {
                int towerMinXRange = tower.getX() - tower.getAttackRange();
                int towerMaxXRange = tower.getX() + tower.getAttackRange();
                int towerMinYRange = tower.getY() - tower.getAttackRange();
                int towerMaxYRange = tower.getY() + tower.getAttackRange();
                Iterator<Monster> iterator = game.getMonstersAlive().iterator();

                while (iterator.hasNext()) {
                    target = iterator.next();
                    if (target.getX() < towerMaxXRange & target.getX() > towerMinXRange & target.getY() > towerMinYRange & target.getY() < towerMaxYRange) {
                        attackService = new Waiting(tower);
                        Thread t = new Thread(attackService::run);
                        t.start();
                        if (tower.isBuild()) {
                            tower.createProjectile(target);
                            target.takeDamage(tower.getAttackDamage());
                        }
                        break;
                    }
                }
            }
        }
    }

}
