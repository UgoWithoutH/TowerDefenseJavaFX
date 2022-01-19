package model.gamelogic.action.states;

import model.characters.Character;
import model.gamelogic.GameState;

public class Updater {

    /**
     *
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
     *
     * @param timer int Boucle de Jeu
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
