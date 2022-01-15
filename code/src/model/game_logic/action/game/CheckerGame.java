package model.game_logic.action.game;

import model.boucleJeu.Boucle;
import model.game_logic.GameState;

import java.util.Scanner;

public class CheckerGame {
    private GameState game;
    private Scanner enemyFile;
    private Boucle boucle;

    public CheckerGame(GameState game, Scanner enemyFile, Boucle boucle) {
        this.game = game;
        this.enemyFile = enemyFile;
        this.boucle = boucle;
    }

    public boolean checkVictory(){
        return (!enemyFile.hasNextLine() && game.getMonstersAlive().isEmpty() && boucle.isRunning());
    }
}
