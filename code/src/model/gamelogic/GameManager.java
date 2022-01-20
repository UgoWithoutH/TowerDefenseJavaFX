package model.gamelogic;

import model.gamelogic.action.ILevel;
import model.gameloop.IObserver;
import model.gameloop.Loop;
import model.characters.Character;
import model.gamelogic.map.Map;
import model.gamelogic.action.IAttacker;
import model.gamelogic.action.IDisplacer;
import model.gamelogic.action.ISpawner;
import model.gamelogic.action.character.SpawnerCharacter;
import model.gamelogic.action.tower.AttackerTower;
import model.gamelogic.action.character.DisplacerCharacters;
import model.gamelogic.action.states.Updater;

/**
 * Classe qui gère la partie
 */
public class GameManager implements IObserver {

    private Map gameMap;
    private GameState game;
    private Loop loop;
    private IDisplacer displacer;
    private AdministratorVictoryGameOver administratorVictoryGameOver;
    private ISpawner spawner;
    private IAttacker attacker;
    private ILevel levelNext;

    /**
     * Creation d'un gameManager et de tout ses éléments
     * @param pseudo    String Pseudo representant le Player
     * @param map   Map a dessiner
     */
    public GameManager(String pseudo, Map map){
        this.gameMap = map;
        game = new GameState(pseudo);
        Character.setPath(gameMap.getPath());
        loop = new Loop();
        loop.subscribe(this);
        displacer = new DisplacerCharacters(game);
        levelNext = new AdministratorLevel(game);
        administratorVictoryGameOver = new AdministratorVictoryGameOver(game, levelNext, loop);
        spawner = new SpawnerCharacter(game, levelNext);
        attacker = new AttackerTower(game.getPlayerTowers(), game.getCharactersAlive());
    }

    public Loop getLoop(){ return loop; }

    public GameState getGame() {
        return game;
    }

    public Map getGameMap() {return gameMap;}

    /**
     * Démarre la boucle de jeu
     */
    public void start() {
        loop.setRunning(true);
        Thread boucleThread = new Thread(loop);
        boucleThread.start();
    }

    /**
     * Update à chaque tour de boucle
     * @param timer int Timer de la boucle de Jeu
     */
    @Override
    public void update(int timer) {
        if (loop.isRunning()) {
            Updater.updateTimerSeconds(timer, loop.getDefaultMillis(), game);

            administratorVictoryGameOver.verifyVictory();

            spawner.spawn(timer);

            administratorVictoryGameOver.verifyGameOver(!displacer.updateLocations());

            attacker.attack();
        }
    }
}
