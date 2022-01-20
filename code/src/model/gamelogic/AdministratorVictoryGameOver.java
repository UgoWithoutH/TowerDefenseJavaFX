package model.gamelogic;

import model.gamelogic.action.ILevel;
import model.gameloop.Loop;

public class AdministratorVictoryGameOver {

    private GameState game;
    private Loop loop;
    private ILevel enemyFile;

    public AdministratorVictoryGameOver(GameState game, ILevel level, Loop boucle) {
        this.game = game;
        this.loop = boucle;
        this.enemyFile=level;
    }

    /**
     * Verifie si l'Etat de la partie est une Victoire
     */
    public void verifyVictory() {
        if(enemyFile instanceof AdministratorLevel administratorLevel) {
            if (!administratorLevel.getLevelFile().hasNextLine() && game.getCharactersAlive().isEmpty() && loop.isRunning()) {
                loop.setRunning(false);
                game.setVictory(true);
            }
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
