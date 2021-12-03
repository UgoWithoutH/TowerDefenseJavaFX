package gameLogic.logicManager;

import gameLogic.GameLogic;
import gameLogic.Map;
import gameLogic.Tower;
import gameLogic.Vector2;

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
