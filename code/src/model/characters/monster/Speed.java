package model.characters.monster;

public class Speed extends Monster{
    public Speed(int healthPoints) {
        super(healthPoints);
        setMovementSpeed(4);
        //view.setFill(Color.GREEN);
    }
}
