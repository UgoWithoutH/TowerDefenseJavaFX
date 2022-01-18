package model.gamelogic;

import model.boucleJeu.Boucle;
import model.gamelogic.action.game.CheckerGame;

import java.util.Scanner;

public class AdministratorVictoryGameOver {
    private GameState game;
    private Boucle boucle;
    private CheckerGame checker;

    public AdministratorVictoryGameOver(GameState game, Scanner enemyFile, Boucle boucle) {
        this.game = game;
        this.boucle = boucle;
        checker = new CheckerGame(game,enemyFile,boucle);
    }

    public void verifyVictory() {
        if (checker.checkVictory()) {
            boucle.setRunning(false);
            game.setVictory(true);
        }
    }

    public void verifyGameOver(boolean value){
        if(!value) return;
        game.setRemoveCharacter(true);
        game.getCharactersAlive().clear();
        boucle.setRunning(false);
        game.setGameOver(true);
    }
}
