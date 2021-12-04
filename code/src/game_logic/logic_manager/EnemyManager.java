package game_logic.logic_manager;

import game_logic.enemy.Enemy;
import jdk.jshell.spi.ExecutionControl;

import java.util.LinkedList;

public class EnemyManager extends LogicManager{
    private LinkedList <Enemy> enemyList;

    public EnemyManager(){
        enemyList = new LinkedList<>();
    }

    public LinkedList<Enemy> getEnemyList() { return enemyList; }

    public void setEnemyList(LinkedList<Enemy> enemyList) { this.enemyList = enemyList; }

    public void EnemyDie(Enemy e){
        enemyList.remove(e);
    }

    public void runAllEnemies(){
        new ExecutionControl.NotImplementedException("");
    }
}
