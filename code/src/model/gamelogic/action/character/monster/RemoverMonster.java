package model.gamelogic.action.character.monster;

import model.characters.Character;
import model.characters.monster.Monster;
import model.gamelogic.GameState;
import model.gamelogic.action.IRemover;

public class RemoverMonster implements IRemover {
    private GameState game;

    public RemoverMonster(GameState game) {
        this.game = game;
    }

    /**
     * Remove Characters in ObservableList<Characters> in GameState
     * @param character Character (Monster)
     */
    @Override
    public void remove(Character character) {
        Monster monster = (Monster) character;
        game.setRemoveCharacter(true);
        game.getCharactersAlive().remove(monster);
        monster.setVisible(false);
        game.setRemoveCharacter(false);
    }
}
