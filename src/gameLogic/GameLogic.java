package gameLogic;

import gameLogic.logicManager.BulletManager;
import gameLogic.logicManager.EnemyManager;
import gameLogic.logicManager.TowerManager;

import java.util.List;

public class GameLogic {
    public TowerManager towerManager;
    public BulletManager bulletManager;
    public EnemyManager enemyManager;

    public Map map;
    public List<Level> levelList;
    public int enemiesKilled = 0;
    public int startingGold = 500;
    public int gold;
    public int currentLevel = 0;



}
