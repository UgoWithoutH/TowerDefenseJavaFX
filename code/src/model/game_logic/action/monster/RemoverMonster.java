package model.game_logic.action.monster;

import model.characters.monster.Monster;
import model.game_logic.GameState;

public class RemoverMonster {
    public static void removeMonster(Monster monster, GameState game){
        game.setRemoveMonster(true);
        game.getMonstersAlive().remove(monster);
        monster.setVisible(false);
        game.setRemoveMonster(false);
    }
}
