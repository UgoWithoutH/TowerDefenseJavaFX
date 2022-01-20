package model.characters;

/**
 * Projectile d'une tour
 */
public class Projectile{
    private final Character target;
    private final int startX;
    private final int startY;

    /**
     * Créé un Projectile de Tour avec une cible Character
     * @param target    Character Cible
     * @param towerX    int Position x de la Tour (Point de départ du Projectile)
     * @param towerY    int Position y de la Tour (Point de départ du Projectile)
     *
     */
    public Projectile(Character target, int towerX, int towerY){
        this.target = target;
        startX = towerX;
        startY = towerY;
    }

    public Character getTarget(){
        return target;
    }

    public int getEndX(){
        return target.getX();
    }
    public int getEndY(){
        return target.getY();
    }

    public int getStartX(){
        return startX;
    }
    public int getStartY(){
        return startY;
    }

}

