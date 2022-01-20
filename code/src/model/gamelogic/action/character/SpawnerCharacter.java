package model.gamelogic.action.character;

import model.characters.monster.Basic;
import model.characters.monster.Speed;
import model.gamelogic.GameState;
import model.gamelogic.action.ILevel;
import model.gamelogic.action.ISpawner;
import model.gamelogic.AdministratorLevel;
import java.util.Scanner;

public class SpawnerCharacter implements ISpawner {

    private GameState game;
    private ILevel level;

    /**
     *Création du système de génération des ennemies en fonction des level recupérés
     * @param game GameState
     * @param level Level pointant sur le fichier des Characters
     */
    public SpawnerCharacter(GameState game, ILevel level) {
        this.game = game;
        this.level = level;
    }

    /**
     * Generation Characters in X timer
     * @param timer int Timer de la Boucle de Jeu
     */
    public void spawn(int timer) {
        if (level instanceof AdministratorLevel administratorLevel) {
            Scanner scannerFile = administratorLevel.getLevelFile();
            if (timer % 40 == 0 && scannerFile.hasNextLine()) {
                switch (scannerFile.next()) {
                    case "Basic" -> game.getCharactersAlive().add(new Basic(5));
                    case "Speed" -> game.getCharactersAlive().add(new Speed(3));
                    default -> game.getCharactersAlive().add(new Basic(3));
                }
            } else if (!scannerFile.hasNextLine()) {
                level.nextLevel();
            }
        }
    }
}
