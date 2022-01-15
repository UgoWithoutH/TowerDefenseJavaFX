package model.game_logic.action.monster;

import model.characters.monster.Basic;
import model.characters.monster.Speed;
import model.game_logic.GameState;

import java.util.Scanner;

public class SpawnerMonster extends Spawner {

    public SpawnerMonster(GameState game, Scanner scannerFile) {
        super(game, scannerFile);
    }

    public void spawnEnemy(int timer) {

        if (timer % 40 == 0 && scannerFile.hasNextLine()) {

            switch (scannerFile.next()) {
                case "Basic":
                    game.getMonstersAlive().add(new Basic(5));
                    break;
                case "Speed":
                    game.getMonstersAlive().add(new Speed(3));
                    break;

                default:
                    game.getMonstersAlive().add(new Basic(3));
                    break;
            }
        }
    }
}
