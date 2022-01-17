package view.creators;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.characters.Projectile;
import model.characters.tower.Tower;
import model.game_logic.GameManager;
import model.game_logic.action.monster.IRemove;
import model.game_logic.action.monster.RemoverMonster;
import model.game_logic.action.states.Update;

public class CreatorProjectiles {

    private IRemove remove;

    private ObjectProperty<Projectile> projectile = new SimpleObjectProperty<>();
        public Projectile getProjectile() {return projectile.get();}
        public ObjectProperty<Projectile> projectileProperty() {return projectile;}
        public void setProjectile(Projectile projectile) {this.projectile.set(projectile);}
    private Group tilemapGroup;
    private GameManager gameManager;


    public CreatorProjectiles(GameManager gameManager, Tower tower, Group tilemapGroup){
        this.tilemapGroup = tilemapGroup;
        this.gameManager = gameManager;
        addListener(tower);
    }

    public void addListener(Tower tower){
        tower.projectileProperty().addListener((observable, oldValue, newValue) -> createProjectiles(newValue));
    }

    public void createProjectiles(Projectile projectile) {
        Path projectilePath;
        PathTransition animation;
        int speedMilis = gameManager.getGame().isSpeed() ? 150 : 300;
        Circle projectileView = new Circle(projectile.getStartX(),projectile.getStartY(),5, Color.BLACK);

        projectilePath = new Path(new MoveTo(projectile.getStartX(), projectile.getStartY()));
        projectilePath.getElements().add(new LineTo(projectile.getEndX(), projectile.getEndY()));
        animation = new PathTransition(Duration.millis(speedMilis), projectilePath, projectileView);
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PathTransition finishedAnimation = (PathTransition) actionEvent.getSource();
                Circle finishedProjectile = (Circle) finishedAnimation.getNode();
                finishedProjectile.setVisible(false);
                tilemapGroup.getChildren().remove(finishedProjectile);
                if (projectile.getTarget().isDead()) {
                    remove.removeMonster(projectile.getTarget(), gameManager.getGame());
                    Update.updateStates(projectile.getTarget(), gameManager.getGame());
                }
            }
        });
        Platform.runLater(() -> tilemapGroup.getChildren().add(projectileView));
        animation.play();
    }
}
