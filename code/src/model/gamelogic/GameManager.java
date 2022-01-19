package model.gamelogic;

import model.gamelogic.action.ILevel;
import model.gamelogic.action.level.Level;
import model.gameloop.IObserver;
import model.gameloop.Loop;
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

import java.io.FileNotFoundException;
import java.util.Scanner;



public class GameManager implements IObserver {

    private Map gameMap;
    private GameState game;
    private Loop loop;
    private DrawMap drawMap;
    private IDisplacer displacer;
    private AdministratorVictoryGameOver administratorVictoryGameOver;
    private ISpawner spawner;
    private IAttacker attacker;
    private ILevel levelNext;

    public GameManager(String pseudo, Map map) throws FileNotFoundException{
        this.gameMap = map;
        game = new GameState(pseudo);
        Character.setPath(gameMap.getPath());
        loop = new Loop();
        loop.subscribe(this);
        displacer = new DisplacerCharacters(game);


        levelNext = new Level(game);
        administratorVictoryGameOver = new AdministratorVictoryGameOver(game, (Level) levelNext, loop);
        spawner = new SpawnerCharacter(game, (Level) levelNext);

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

    /**
     * Demarre la boucle de jeu
     */
    public void start() {
        loop.setRunning(true);
        Thread boucleThread = new Thread(loop);
        boucleThread.start();
    }

    /**
     * Update a chaque tour de Boucle
     * @param timer int Timer de la boucle de Jeu
     */
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
