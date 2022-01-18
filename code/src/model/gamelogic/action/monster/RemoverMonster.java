package model.gamelogic.action.monster;

import model.characters.Character;
import model.characters.monster.Monster;
import model.gamelogic.GameState;
import model.gamelogic.action.Remover;

public class RemoverMonster implements Remover {

    @Override
    public void remove(Character character, GameState game) {
        Monster monster = (Monster) character;
        game.setRemoveCharacter(true);
        game.getCharactersAlive().remove(monster);
        monster.setVisible(false);
        game.setRemoveCharacter(false);
    }
}
