package model.characters.monster;

public class Basic extends Monster {

    private static final int DEFAULT_MOVEMENT_SPEED = 1;

    public Basic(int healthPoints) {
        super(healthPoints,DEFAULT_MOVEMENT_SPEED);
    }
}
