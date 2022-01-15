package model.game_logic;

import model.boucleJeu.Boucle;
import model.boucleJeu.Observateur;
import model.characters.monster.Monster;
import model.game_logic.Map.Map;
import model.game_logic.action.monster.SpawnerMonster;
import view.map.DrawMap;
import model.game_logic.action.monster.Displacer;
import model.game_logic.action.monster.DisplacerMonsters;
import model.game_logic.action.monster.Spawner;
import model.game_logic.action.states.Update;
import model.game_logic.action.tower.TowerAction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GameManager implements Observateur {

    private Map gameMap;
    private GameState game;
    private Boucle boucle;
    private DrawMap drawMap;
    private Displacer displacer;
    private GestionnaireVictoryGameOver gestionnaireGame;
    private Spawner spawner;

    public GameManager(String pseudo, Map map) throws FileNotFoundException{
        this.gameMap = map;
        game = new GameState(pseudo);
        Monster.setPath(gameMap.getPath());
        boucle = new Boucle();
        boucle.subscribe(this);
        displacer = new DisplacerMonsters(game);
        Scanner scannerMonster = new Scanner(new File(System.getProperty("user.dir")+ "/code/ressources/Level/Level1/EnemyFile.txt"));
        gestionnaireGame = new GestionnaireVictoryGameOver(game,scannerMonster,boucle);
        spawner = new SpawnerMonster(game,scannerMonster);
    }

    public Boucle getBoucle(){ return boucle; }

    public DrawMap getDrawMap() {
        return drawMap;
    }

    public GameState getGame() {
        return game;
    }

    public Map getGameMap() {
        return gameMap;
    }


    public void setDrawMap(DrawMap drawMap) {
        this.drawMap = drawMap;
    }


    public void start() {
        boucle.setRunning(true);
        Thread boucleThread = new Thread(boucle);
        boucleThread.start();
    }

    @Override
    public void update(int timer) {
        if (boucle.isRunning()) {
            Update.updateTimerSeconds(timer, boucle.getDefaultMilis(), game);

            gestionnaireGame.verifyVictory();

            spawner.spawnEnemy(timer);

            gestionnaireGame.verifyGameOver(!displacer.updateLocations());

            TowerAction.attackTower(game);
        }
    }
}
