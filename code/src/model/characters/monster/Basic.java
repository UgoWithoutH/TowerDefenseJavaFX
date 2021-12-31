package model.characters.monster;

import javafx.scene.paint.Color;

public class Basic extends Monster {

    public Basic(int healthPoints) {
        super(healthPoints);
        view.setFill(Color.RED);
    }
}
