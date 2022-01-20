package model.gamelogic.action.states;

import model.characters.Character;
import model.gamelogic.GameState;

/**
 * Classe mettant à jour des informations de la partie
 */
public class Updater {

    /**
     * Met a Jour le score en fonction de la position des Characters
     * @param character Character
     * @param game  GameState
     */
    public static void updateStates(Character character, GameState game) {
        if (character.isPathFinished()) {
            game.setLives((game.getLives()) - 1);
        } else {
            game.setCoins((game.getCoins()) + character.getReward());
            game.setScore(game.getScore() + (character.getReward() * (game.getLevel() + 1)));
        }
    }

    /**
     * Met a jour le timer du jeu par rapport à un timer (boucle de jeu)
     * @param timer int
     * @param millis    long
     * @param game  GameState
     */
    public static void updateTimerSeconds(int timer, long millis, GameState game){
        var timeMillis = timer * millis;
        var timeSeconds = (int) (timeMillis / 1000);

        if (timeSeconds != game.getTimeSeconds()) {
            game.setTimeSeconds(game.getTimeSeconds() + 1);
        }
    }
}
