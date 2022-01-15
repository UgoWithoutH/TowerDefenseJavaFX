package model.game_logic.action.monster;

import model.game_logic.GameState;

public abstract class Displacer {
    protected GameState game;

    public Displacer(GameState game) {this.game = game;}

    public abstract boolean updateLocations();
}
