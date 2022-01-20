package model.gamelogic.action.character;
import model.characters.Character;
import model.characters.monster.Monster;
import model.gamelogic.GameState;
import model.gamelogic.action.IDisplacer;
import model.gamelogic.action.IRemover;
import model.gamelogic.action.character.monster.RemoverMonster;
import model.gamelogic.action.states.Updater;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Déplaceur de caractères
 */
public class DisplacerCharacters implements IDisplacer {

    private GameState game;
    private IRemover remover;

    public DisplacerCharacters(GameState game) {this.game = game;}

    /**
     * Modifie la position de tous les Character dans Observable List -> GameState
     * @return
     */
    @Override
    public boolean updateLocations() {
        ArrayList<Character> charactersEnd = new ArrayList<>();
        var listCharacters = game.getCharactersAlive();
        if (!listCharacters.isEmpty()) {
            Iterator<Character> characterIterator = listCharacters.iterator();
            Character character;
            while (characterIterator.hasNext()) {
                character = characterIterator.next();
                character.updateLocation();
                if (character.isPathFinished()) {
                    Updater.updateStates(character, game);
                    charactersEnd.add(character);
                    if (game.getLives() == 0) {
                        return false;
                    }
                }
            }
            for (Character characterDelete : charactersEnd) {
                if(characterDelete instanceof Monster){
                    remover = new RemoverMonster(game);
                }
                remover.remove(characterDelete);
            }
        }
        return true;
    }
}
