package view.view_factory;

import model.game_logic.GameManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.characters.Projectile;
import model.characters.tower.Tower;

public class AnimationBasicTowerFactory implements AnimationTowerFactory {
    private GameManager gameManager;
    private Group tilemapGroup;

    public AnimationBasicTowerFactory(GameManager gameManager){
        this.gameManager = gameManager;
    }

    public void setTilemapGroup(Group tilemapGroup) {
        this.tilemapGroup = tilemapGroup;
    }

    public void createProjectiles(){
        Path projectilePath;
        PathTransition animation;
        for(Tower tower : gameManager.getGame().getPlayerTowers()){
            for(Projectile projectile : tower.getProjectileList()){
                projectilePath = new Path(new MoveTo(projectile.getStartX() , projectile.getStartY()));
                projectilePath.getElements().add(new LineTo(projectile.getEndX() , projectile.getEndY()));
                animation = new PathTransition(Duration.millis(300) , projectilePath , projectile);
                animation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        PathTransition finishedAnimation = (PathTransition) actionEvent.getSource();
                        Projectile finishedProjectile = (Projectile) finishedAnimation.getNode();
                        finishedProjectile.setVisible(false);
                        tilemapGroup.getChildren().remove(finishedProjectile);
                        if(finishedProjectile.getTarget().isDead()){
                            gameManager.removeMonster(finishedProjectile.getTarget());
                            gameManager.updateStates(finishedProjectile.getTarget());
                        }
                    }
                });
                Platform.runLater(() -> tilemapGroup.getChildren().add(projectile));
                animation.play();
            }
            tower.getProjectileList().clear();
        }
    }

    public void createBuildProgressBar(double xCords, double yCords, Tower t) {
        Group g = new Group();
        g.setLayoutX(xCords-32);
        g.setLayoutY(yCords-32);
        ProgressBar bar = new ProgressBar();
        Timeline task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(bar.progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(t.getBuildTimeSeconds()),
                        new KeyValue(bar.progressProperty(), 1)
                )
        );
        task.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bar.setVisible(false);
            }
        });
        g.getChildren().add(bar);
        tilemapGroup.getChildren().add(g);
        task.playFromStart();
    }

}
