package game_logic.logic_manager;

import game_logic.GameLogic;
import game_logic.Map;
import game_logic.tower.Tower;
import game_logic.Vector2;

import java.util.List;

public class TowerManager extends LogicManager{
    private Tower tower1;
    private List<Tower> towerList;
    private Map map;
    private GameLogic gameLogic;

    public TowerManager(Tower tower1, List<Tower> towerList, Map map, GameLogic gameLogic) {
        this.tower1 = tower1;
        this.towerList = towerList;
        this.map = map;
        this.gameLogic = gameLogic;
    }

    public void addTower(int towerType, Vector2 position, int gold)
    {

    }

}
