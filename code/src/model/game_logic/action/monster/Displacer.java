package model.game_logic.action.monster;

import model.characters.monster.Monster;
import model.game_logic.GameState;
import model.game_logic.action.states.Update;

import java.util.ArrayList;
import java.util.Iterator;

public class Displacer {
    public static boolean updateLocations(GameState game) {
        ArrayList<Monster> monsterEnd = new ArrayList<>();
        if (!game.getMonstersAlive().isEmpty()) {
            Iterator<Monster> monsters = game.getMonstersAlive().iterator();
            Monster monster;
            while (monsters.hasNext()) {
                monster = monsters.next();
                monster.updateLocation(monster.getMovementSpeed());
                if (monster.isPathFinished()) {
                    Update.updateStates(monster,game);
                    monsterEnd.add(monster);
                    if (game.getLives() == 0) {
                        return false;
                    }
                }
            }
            for (Monster m : monsterEnd) {
                Remover.removeMonster(m,game);
            }
        }
        return true;
    }
}
