package model.game_logic.action.monster;

import model.characters.monster.Monster;
import model.game_logic.GameState;

public interface IRemove {
    void removeMonster(Monster monster, GameState game);
}
