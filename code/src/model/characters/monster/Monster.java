package model.characters.monster;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.characters.Character;

public class Monster extends Character {

    private BooleanProperty visible = new SimpleBooleanProperty();
        public boolean isVisible() {return visible.get();}
        public BooleanProperty visibleProperty() {return visible;}
        public void setVisible(boolean visible) {this.visible.set(visible);}

    public Monster(int healthPoints, int movementSpeed) {
        super(healthPoints,movementSpeed);
        setVisible(true);
    }

    /**
     *  Damage take-in
     * @param damage int
     */
    public void takeDamage(int damage) {
        setHealthPoints(getHealthPoints() - damage);
        if (getHealthPoints() <= 0) {
            setDead(true);
            setPathFinished(false);
        }
    }
}
