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

    /**
     * Verifie si l'Etat de la partie est une Victoire
     */
    public void verifyVictory() {
        if (!enemyFile.hasNextLine() && game.getCharactersAlive().isEmpty() && loop.isRunning()) {
            loop.setRunning(false);
            game.setVictory(true);
        }
    }

    /**
     * Verifie si l'Etat de la partie est une Defaite
     * @param value none
     */
    public void verifyGameOver(boolean value){
        if(!value) return;
        game.setRemoveCharacter(true);
        game.getCharactersAlive().clear();
        loop.setRunning(false);
        game.setGameOver(true);
    }
}
