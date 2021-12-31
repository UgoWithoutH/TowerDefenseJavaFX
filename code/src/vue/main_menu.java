package vue;


import game_logic.GameManager;
import game_logic.GameViewLogic;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.Map.importMap;
import model.characters.Projectile;
import model.characters.Tower;
import update.DrawMap;

import java.io.IOException;
import java.net.URISyntaxException;

public class main_menu implements GameViewLogic {


    private  Scene gameScene;
    private vue.game gameController;
    private Group tilemapGroup;
    private GameManager gameManager;


    public void startNewGame() throws URISyntaxException {
        try{
            gameManager = new GameManager();
            gameManager.initialize(this, new importMap(1216 ,608));
            gameManager.setDrawMap(new DrawMap(gameManager.getGameMap()));
            FXMLLoader loader = new FXMLLoader(Navigator.GAMEUI);
            GridPane gamePane = new GridPane();
            //gamePane.setAlignment(Pos.TOP_LEFT);
            tilemapGroup = new Group();
            tilemapGroup.getChildren().add(gameManager.getDrawMap());
            gamePane.add(tilemapGroup,0,0);

            Node gameUI = (Node) loader.load(Navigator.GAMEUI.openStream());
            gameUI.setStyle("-fx-background-color: grey;");
            gamePane.add(gameUI,0,1);
            gameScene = new Scene(gamePane);
            gameScene.getStylesheets().add(GameManager.class.getResource("/FXML/gamestyle.css").toExternalForm());
            gameController = loader.getController();
            gameController.setGameManager(gameManager);
            gameController.setScene(gameScene);
            Navigator.stage.setScene(gameScene);
            gameManager.start();
        }catch (IOException ex){ex.printStackTrace();}
    }

    public void createMonster(int health){
        GameManager gameManager = gameController.getGameManager();
        tilemapGroup.getChildren().add(gameManager.getGame().getMonstersAlive().get(gameManager.getGame().getMonstersAlive().size() - 1).getView());
    }


    public void exitGame(){
        System.exit(1);
    }

    public void createProjectiles(){
        Path projectilePath;
        PathTransition animation;
        for(Tower tower : gameManager.getGame().getPlayerTowers()){
            for(Projectile projectile : tower.getProjectileList()){
                // Create animation path
                projectilePath = new Path(new MoveTo(projectile.getStartX() , projectile.getStartY()));
                projectilePath.getElements().add(new LineTo(projectile.getEndX() , projectile.getEndY()));
                animation = new PathTransition(Duration.millis(300) , projectilePath , projectile);

                // When the animation finishes, hide it and remove it
                animation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        PathTransition finishedAnimation = (PathTransition) actionEvent.getSource();
                        Projectile finishedProjectile = (Projectile) finishedAnimation.getNode();

                        // Hide and remove from gui
                        finishedProjectile.setVisible(false);
                        tilemapGroup.getChildren().remove(finishedProjectile);

                        // Remove monster if they are dead
                        if(finishedProjectile.getTarget().isDead()){
                            gameManager.removeMonster(finishedProjectile.getTarget());
                        }
                    }
                });
                Platform.runLater(() -> tilemapGroup.getChildren().add(projectile));
                animation.play();
            }
            tower.getProjectileList().clear();
        }
    }

    @Override
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