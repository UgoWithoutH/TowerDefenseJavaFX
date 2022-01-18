package model.gamelogic.action.monster;
import model.characters.Character;
import model.gamelogic.GameState;
import model.gamelogic.action.Displacer;
import model.gamelogic.action.Remover;
import model.gamelogic.action.states.Updater;

import java.util.ArrayList;
import java.util.Iterator;

public class DisplacerCharacters implements Displacer {

    private GameState game;

    public DisplacerCharacters(GameState game) {this.game = game;}

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
                Remover remover = new RemoverMonster();
                remover.remove(CharacterDelete,game);
            }
        }
        return true;
    }
}
