package model.game_logic.action.monster;

import model.characters.monster.Basic;
import model.characters.monster.Speed;
import model.game_logic.GameState;

import java.util.Scanner;

public abstract class Spawner {
    protected GameState game;
    protected Scanner scannerFile;

    public Spawner(GameState game, Scanner scannerFile) {
        this.game = game;
        this.scannerFile = scannerFile;
    }

    public abstract void spawnEnemy(int timer);
}
