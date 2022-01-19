package model.gamelogic;

import model.gameloop.Loop;

import java.util.Scanner;

public class AdministratorVictoryGameOver {
    private GameState game;
    private Loop loop;
    private Scanner enemyFile;

    public AdministratorVictoryGameOver(GameState game, Scanner enemyFile, Loop boucle) {
        this.game = game;
        this.loop = boucle;
        this.enemyFile = enemyFile;
    }

    public void verifyVictory() {
        if (!enemyFile.hasNextLine() && game.getCharactersAlive().isEmpty() && loop.isRunning()) {
            loop.setRunning(false);
            game.setVictory(true);
        }
    }

    public void verifyGameOver(boolean value){
        if(!value) return;
        game.setRemoveCharacter(true);
        game.getCharactersAlive().clear();
        loop.setRunning(false);
        game.setGameOver(true);
    }
}
