package view.informer;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.Coordinate;
import model.characters.Projectile;
import model.characters.tower.Tower;
import model.game_logic.GameManager;

public class InformerProjectiles {

    private ObjectProperty<Projectile> projectile = new SimpleObjectProperty<>();
        public Projectile getProjectile() {return projectile.get();}
        public ObjectProperty<Projectile> projectileProperty() {return projectile;}
        public void setProjectile(Projectile projectile) {this.projectile.set(projectile);}
    private Tower tower;


    public InformerProjectiles(Tower tower){
        this.tower = tower;
        addListener(tower);
    }

    public void addListener(Tower tower){

    }
}
