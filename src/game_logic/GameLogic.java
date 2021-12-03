package game_logic;

import game_logic.logic_manager.BulletManager;
import game_logic.logic_manager.EnemyManager;
import game_logic.logic_manager.TowerManager;

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
