package model.game_logic.action.states;

import model.characters.monster.Monster;
import model.game_logic.GameState;

public class Update {
    public static void updateStates(Monster monster, GameState game) {

        if (monster.isPathFinished()) {
            game.setLives((game.getLives()) - 1);
        } else {
            game.setCoins((game.getCoins()) + monster.getReward());
            game.setScore(game.getScore() + (monster.getReward() * (game.getLevel() + 1)));
        }
    }

    public static void updateTimerSeconds(int timer,long milis,GameState game){
        var timeMilis = timer * milis;
        var timeSeconds = (int) (timeMilis / 1000);

        if (timeSeconds != game.getTimeSeconds()) {
            game.setTimeSeconds(game.getTimeSeconds() + 1);
        }
        //System.out.println(game.getTimeSeconds());
    }
}
