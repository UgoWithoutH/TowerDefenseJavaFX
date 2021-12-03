package gameLogic;

public class Level {

    int levelNo, spawnTime, enemyCount, enemyType;
    //Constructor to initalize new level with given parameters
    public Level(int levelNo, int spawnTime, int enemyCount, int enemyType)
    {
        this.levelNo = levelNo;
        this.spawnTime = spawnTime;
        this.enemyCount = enemyCount;
        this.enemyType = enemyType;
    }

    //returns enemyCount
    public int getEnemyCount()
    {
        return enemyCount;
    }
    //returns spawnTime
    public int getSpawnTime()
    {
        return spawnTime;
    }
    //returns levelNo
    public int getLevelNo()
    {
        return levelNo;
    }
    //returns enemyType
    public int getEnemyType()
    {
        return enemyType;
    }
}
