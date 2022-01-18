package model.gamelogic;

import model.boucleJeu.Boucle;
import model.boucleJeu.Observateur;
import model.characters.Character;
import model.gamelogic.map.Map;
import model.gamelogic.action.Attacker;
import model.gamelogic.action.Displacer;
import model.gamelogic.action.Spawner;
import model.gamelogic.action.monster.SpawnerMonster;
import model.gamelogic.action.tower.AttackerTower;
import view.map.DrawMap;
import model.gamelogic.action.monster.DisplacerCharacters;
import model.gamelogic.action.states.Updater;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GameManager implements Observateur {

    private Map gameMap;
    private GameState game;
    private Boucle boucle;
    private DrawMap drawMap;
    private Displacer displacer;
    private AdministratorVictoryGameOver gestionnaireGame;
    private Spawner spawner;
    private Attacker attacker;

    public GameManager(String pseudo, Map map) throws FileNotFoundException{
        this.gameMap = map;
        game = new GameState(pseudo);
        Character.setPath(gameMap.getPath());
        boucle = new Boucle();
        boucle.subscribe(this);
        displacer = new DisplacerCharacters(game);
        Scanner scannerMonster = new Scanner(new File(System.getProperty("user.dir")+ "/code/ressources/levels/level1.txt"));
        gestionnaireGame = new AdministratorVictoryGameOver(game,scannerMonster,boucle);
        spawner = new SpawnerMonster(game,scannerMonster);
        attacker = new AttackerTower(game.getPlayerTowers(), game.getCharactersAlive());
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
            Updater.updateTimerSeconds(timer, boucle.getDefaultMilis(), game);

            gestionnaireGame.verifyVictory();

            spawner.spawnEnemy(timer);

            gestionnaireGame.verifyGameOver(!displacer.updateLocations());

            attacker.attack();
        }
    }
}
