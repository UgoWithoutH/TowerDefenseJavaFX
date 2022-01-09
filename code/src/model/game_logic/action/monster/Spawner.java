package model.game_logic.action.monster;

import model.characters.monster.Basic;
import model.characters.monster.Speed;
import model.game_logic.GameState;

public class Spawner {
    public static void spawnEnemy(String type, GameState game) throws CloneNotSupportedException {
        switch (type) {
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
