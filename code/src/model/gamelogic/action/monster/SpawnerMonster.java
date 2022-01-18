package model.gamelogic.action.monster;

import model.characters.monster.Basic;
import model.characters.monster.Speed;
import model.gamelogic.GameState;
import model.gamelogic.action.Spawner;

import java.util.Scanner;

public class SpawnerMonster implements Spawner {

    private GameState game;
    private Scanner scannerFile;

    public SpawnerMonster(GameState game, Scanner scannerFile) {
        this.game = game;
        this.scannerFile = scannerFile;
    }

    public void spawnEnemy(int timer) {

        if (timer % 40 == 0 && scannerFile.hasNextLine()) {

            switch (scannerFile.next()) {
                case "Basic":
                    game.getCharactersAlive().add(new Basic(5));
                    break;
                case "Speed":
                    game.getCharactersAlive().add(new Speed(3));
                    break;

                default:
                    game.getCharactersAlive().add(new Basic(3));
                    break;
            }
        }
    }
}
