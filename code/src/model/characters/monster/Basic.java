package model.characters.monster;

/**
 * Monstre basique
 */
public class Basic extends Monster {

    private static final int DEFAULT_MOVEMENT_SPEED = 1;

    /**
     * Creer un Monstre Basic avec un choix du nombre de point de vie
     * @param healthPoints int Nombre de points de vie
     */
    public Basic(int healthPoints) {
        super(healthPoints,DEFAULT_MOVEMENT_SPEED);
    }
}
