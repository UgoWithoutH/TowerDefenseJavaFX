package model.gamelogic.action.character;

import model.characters.monster.Basic;
import model.characters.monster.Speed;
import model.gamelogic.GameState;
import model.gamelogic.action.ISpawner;

import java.util.Scanner;

public class SpawnerCharacter implements ISpawner {

    private GameState game;
    private Scanner scannerFile;

    /**
     *
     * @param game GameState
     * @param scannerFile Scanner pointant sur le fichier des Characters
     */
    public SpawnerCharacter(GameState game, Scanner scannerFile) {
        this.game = game;
        this.scannerFile = scannerFile;
    }

    /**
     * Generation Characters in X timer
     * @param timer int Timer de la Boucle de Jeu
     */
    public void spawnEnemy(int timer) {

        if (timer % 40 == 0 && scannerFile.hasNextLine()) {
            switch (scannerFile.next()) {
                case "Basic" -> game.getCharactersAlive().add(new Basic(5));
                case "Speed" -> game.getCharactersAlive().add(new Speed(3));
                default -> game.getCharactersAlive().add(new Basic(3));
            }
        }
    }
}
