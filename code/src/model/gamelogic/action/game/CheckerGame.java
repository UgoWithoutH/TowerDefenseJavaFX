package model.gamelogic.action.game;

import model.boucleJeu.Boucle;
import model.gamelogic.GameState;

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
        return (!enemyFile.hasNextLine() && game.getCharactersAlive().isEmpty() && boucle.isRunning());
    }
}
