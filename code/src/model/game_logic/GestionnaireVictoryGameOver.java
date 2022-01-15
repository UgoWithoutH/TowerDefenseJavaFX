package model.game_logic;

import model.boucleJeu.Boucle;
import model.game_logic.action.game.CheckerGame;

import java.util.Scanner;

public class GestionnaireVictoryGameOver {
    private GameState game;
    private Boucle boucle;
    private CheckerGame checker;

    public GestionnaireVictoryGameOver(GameState game, Scanner enemyFile, Boucle boucle) {
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
        game.setRemoveMonster(true);
        game.getMonstersAlive().clear();
        boucle.setRunning(false);
        game.setGameOver(true);
    }
}
