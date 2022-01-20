package model.characters.monster;

/**
 * Monstre rapide
 */
public class Speed extends Monster{

    private static final int DEFAULT_MOVEMENT_SPEED = 4;

    /**
     * Creer un Monstre Speed avec un choix du nombre de point de vie.
     * Par rapport au montre Basic, il est plus rapide
     * @param healthPoints int Nombre de points de vie
     */
    public Speed(int healthPoints) {
        super(healthPoints,DEFAULT_MOVEMENT_SPEED);
    }

}
