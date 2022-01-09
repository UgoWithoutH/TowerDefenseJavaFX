package model.game_logic.action.monster;

import model.characters.monster.Monster;
import model.game_logic.GameState;

public class Remover {
    public static void removeMonster(Monster monster, GameState game){
        game.setRemoveMonster(true);
        game.getMonstersAlive().remove(monster);
        game.setRemoveMonster(false);
        monster.getView().setVisible(false);
    }
}
