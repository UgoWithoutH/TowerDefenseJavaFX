package model.characters.monster;

import javafx.scene.paint.Color;

public class Speed extends Monster{
    public Speed(int healthPoints) {
        super(healthPoints);
        movementSpeed=4;
        view.setFill(Color.GREEN);
    }
}
