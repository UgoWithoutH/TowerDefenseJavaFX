package model.characters.monster;

public class Speed extends Monster{

    private static final int DEFAULT_MOVEMENT_SPEED = 4;

    public Speed(int healthPoints) {
        super(healthPoints,DEFAULT_MOVEMENT_SPEED);
    }

}
