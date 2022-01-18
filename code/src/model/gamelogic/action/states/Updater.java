package model.gamelogic.action.states;

import model.characters.Character;
import model.gamelogic.GameState;

public class Updater {

    /**
     * Update State Monster
     *
     * @param character
     * @param game
     */
    public static void updateStates(Character character, GameState game) {

        if (character.isPathFinished()) {
            game.setLives((game.getLives()) - 1);
        } else {
            game.setCoins((game.getCoins()) + character.getReward());
            game.setScore(game.getScore() + (character.getReward() * (game.getLevel() + 1)));
        }
    }

    public static void updateTimerSeconds(int timer,long milis,GameState game){
        var timeMilis = timer * milis;
        var timeSeconds = (int) (timeMilis / 1000);

        if (timeSeconds != game.getTimeSeconds()) {
            game.setTimeSeconds(game.getTimeSeconds() + 1);
        }
    }
}
