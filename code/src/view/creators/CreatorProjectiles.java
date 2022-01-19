package view.creators;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import model.characters.monster.Monster;
import model.characters.tower.Tower;
import model.gamelogic.GameManager;
import model.gamelogic.action.IRemover;
import model.gamelogic.action.character.monster.RemoverMonster;
import model.gamelogic.action.states.Updater;

public class CreatorProjectiles {

    private ObjectProperty<Projectile> projectile = new SimpleObjectProperty<>();
        public Projectile getProjectile() {return projectile.get();}
        public ObjectProperty<Projectile> projectileProperty() {return projectile;}
        public void setProjectile(Projectile projectile) {this.projectile.set(projectile);}
    private Group tilemapGroup;
    private GameManager gameManager;


    public CreatorProjectiles(GameManager gameManager, Tower tower, Group tilemapGroup){
        this.tilemapGroup = tilemapGroup;
        this.gameManager = gameManager;
        tower.projectileProperty().addListener((observable, oldValue, newValue) -> createProjectiles(newValue));
    }

    public void createProjectiles(Projectile projectile) {
        Path projectilePath;
        PathTransition animation;
        int speedMillis = gameManager.getGame().isSpeed() ? 150 : 300;
        Circle projectileView = new Circle(projectile.getStartX(),projectile.getStartY(),5, Color.BLACK);

        projectilePath = new Path(new MoveTo(projectile.getStartX(), projectile.getStartY()));
        projectilePath.getElements().add(new LineTo(projectile.getEndX(), projectile.getEndY()));
        animation = new PathTransition(Duration.millis(speedMillis), projectilePath, projectileView);
        animation.setOnFinished(actionEvent -> {
                PathTransition finishedAnimation = (PathTransition) actionEvent.getSource();
                Circle finishedProjectile = (Circle) finishedAnimation.getNode();
                finishedProjectile.setVisible(false);
                tilemapGroup.getChildren().remove(finishedProjectile);
                if (projectile.getTarget().isDead()) {
                    IRemover remover;
                    if(projectile.getTarget() instanceof Monster monster){
                        remover = new RemoverMonster(gameManager.getGame());
                        remover.remove(monster);
                    }
                    Updater.updateStates(projectile.getTarget(), gameManager.getGame());
            }
        });
        Platform.runLater(() -> tilemapGroup.getChildren().add(projectileView));
        animation.play();
    }
}
