package model.characters.monster;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.characters.Character;

/**
 * Définit un Monster à partir d'un Character
 */
public class Monster extends Character {

    /**
     * Variable qui définit si le Monstre est visible ou non
     */
    private BooleanProperty visible = new SimpleBooleanProperty();
        public boolean isVisible() {return visible.get();}
        public BooleanProperty visibleProperty() {return visible;}
        public void setVisible(boolean visible) {this.visible.set(visible);}

    /**
     * Constructeur de Monstre
     * @param healthPoints  int Nombre Point de Vie
     * @param movementSpeed int Vitesse de Mouvement
     */
    public Monster(int healthPoints, int movementSpeed) {
        super(healthPoints,movementSpeed);
        setVisible(true);
    }

    /**
     *  Dommages reçus
     * @param damage int Valeur des dégats
     */
    public void takeDamage(int damage) {
        setHealthPoints(getHealthPoints() - damage);
        if (getHealthPoints() <= 0) {
            setDead(true);
            setPathFinished(false);
        }
    }
}
