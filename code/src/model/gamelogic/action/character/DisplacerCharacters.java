package model.gamelogic.action.character;
import model.characters.Character;
import model.gamelogic.GameState;
import model.gamelogic.action.IDisplacer;
import model.gamelogic.action.IRemover;
import model.gamelogic.action.character.monster.RemoverMonster;
import model.gamelogic.action.states.Updater;

import java.util.ArrayList;
import java.util.Iterator;

public class DisplacerCharacters implements IDisplacer {

    private GameState game;

    public DisplacerCharacters(GameState game) {this.game = game;}

    /**
     * Update Location of all Character in Observable List
     * @return
     */
    @Override
    public boolean updateLocations() {
        ArrayList<Character> CharactersEnd = new ArrayList<>();
        var listCharacters = game.getCharactersAlive();
        if (!listCharacters.isEmpty()) {
            Iterator<Character> characterIterator = listCharacters.iterator();
            Character character;
            while (characterIterator.hasNext()) {
                character = characterIterator.next();
                character.updateLocation();
                if (character.isPathFinished()) {
                    Updater.updateStates(character, game);
                    CharactersEnd.add(character);
                    if (game.getLives() == 0) {
                        return false;
                    }
                }
            }
            for (Character CharacterDelete : CharactersEnd) {
                IRemover remover = new RemoverMonster(game);
                remover.remove(CharacterDelete);
            }
        }
        return true;
    }
}
