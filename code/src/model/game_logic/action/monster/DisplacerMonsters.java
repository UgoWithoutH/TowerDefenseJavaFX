package model.game_logic.action.monster;

import model.characters.monster.Monster;
import model.game_logic.GameState;
import model.game_logic.action.states.Update;

import java.util.ArrayList;
import java.util.Iterator;

public class DisplacerMonsters extends Displacer{

    public DisplacerMonsters(GameState game){super(game);}

    @Override
    public boolean updateLocations() {
        ArrayList<Monster> monsterEnd = new ArrayList<>();
        if (!game.getMonstersAlive().isEmpty()) {
            Iterator<Monster> monsters = game.getMonstersAlive().iterator();
            Monster monster;
            while (monsters.hasNext()) {
                monster = monsters.next();
                monster.updateLocation(monster.getMovementSpeed());                 // todo notifier la vue pour déplacer monster
                if (monster.isPathFinished()) {
                    Update.updateStates(monster, game);
                    monsterEnd.add(monster);
                    if (game.getLives() == 0) {
                        return false;
                    }
                }
            }
            for (Monster m : monsterEnd) {
                RemoverMonster.removeMonster(m,game);
            }
        }
        return true;
    }
}
