package model.gamelogic.action.character.monster;

import model.characters.Character;
import model.characters.monster.Monster;
import model.gamelogic.GameState;
import model.gamelogic.action.Remover;

public class RemoverMonster implements Remover {
    private GameState game;

    public RemoverMonster(GameState game) {
        this.game = game;
    }

    @Override
    public void remove(Character character) {
        Monster monster = (Monster) character;
        game.setRemoveCharacter(true);
        game.getCharactersAlive().remove(monster);
        monster.setVisible(false);
        game.setRemoveCharacter(false);
    }
}
