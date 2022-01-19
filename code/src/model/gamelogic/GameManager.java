package model.gamelogic;

import model.boucleJeu.Loop;
import model.boucleJeu.IObservateur;
import model.characters.Character;
import model.gamelogic.map.Map;
import model.gamelogic.action.IAttacker;
import model.gamelogic.action.IDisplacer;
import model.gamelogic.action.ISpawner;
import model.gamelogic.action.character.SpawnerCharacter;
import model.gamelogic.action.tower.AttackerTower;
import view.map.DrawMap;
import model.gamelogic.action.character.DisplacerCharacters;
import model.gamelogic.action.states.Updater;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GameManager implements IObservateur {

    private Map gameMap;
    private GameState game;
    private Loop loop;
    private DrawMap drawMap;
    private IDisplacer displacer;
    private AdministratorVictoryGameOver administratorVictoryGameOver;
    private ISpawner spawner;
    private IAttacker attacker;

    public GameManager(String pseudo, Map map) throws FileNotFoundException{
        this.gameMap = map;
        game = new GameState(pseudo);
        Character.setPath(gameMap.getPath());
        loop = new Loop();
        loop.subscribe(this);
        displacer = new DisplacerCharacters(game);
        Scanner scannerMonster = new Scanner(new File(System.getProperty("user.dir")+ "/code/ressources/levels/level1.txt"));
        administratorVictoryGameOver = new AdministratorVictoryGameOver(game,scannerMonster, loop);
        spawner = new SpawnerCharacter(game,scannerMonster);
        attacker = new AttackerTower(game.getPlayerTowers(), game.getCharactersAlive());
    }

    public Loop getLoop(){ return loop; }

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
        loop.setRunning(true);
        Thread boucleThread = new Thread(loop);
        boucleThread.start();
    }

    @Override
    public void update(int timer) {
        if (loop.isRunning()) {
            Updater.updateTimerSeconds(timer, loop.getDefaultMillis(), game);

            administratorVictoryGameOver.verifyVictory();

            spawner.spawnEnemy(timer);

            administratorVictoryGameOver.verifyGameOver(!displacer.updateLocations());

            attacker.attack();
        }
    }
}
