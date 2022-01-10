package view;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Coordinate;
import model.Manager;
import model.characters.Projectile;
import model.characters.monster.Monster;
import model.characters.tower.Tower;
import model.game_logic.GameManager;
import model.game_logic.GameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Map.importMap;
import model.Map.update.DrawMap;
import model.game_logic.action.monster.Remover;
import model.game_logic.action.states.Update;
import view.informer.InformerMonsters;
import view.informer.InformerProjectiles;

import java.io.IOException;
import java.net.URISyntaxException;

public class main_menu {


    private  Scene gameScene;
    private  Scene optionScene;
    private view.game gameController;
    private Group tilemapGroup;
    private Manager manager = Navigator.getManager();
    @FXML
    private ListView scoreList;

    @FXML
    public void initialize(){
        scoreList.setStyle("-fx-background-color: linear-gradient(#bab9b9, #777777);");
        scoreList.itemsProperty().bind(manager.getScoreRanking().rankingProperty());
        scoreList.setCellFactory(__ ->
                new ListCell<GameState>(){
                    @Override
                    protected void updateItem(GameState gameState, boolean empty) {
                        super.updateItem(gameState, empty);
                        if (!empty) {
                            Label l1Text = new Label("Level : ");
                            Label l1 = new Label();
                            HBox myHbox1 = new HBox(l1Text,l1);
                            l1.textProperty().bind(gameState.levelProperty().asString());
                            Label l2Text = new Label("Score : ");
                            Label l2 = new Label();
                            HBox myHbox2 = new HBox(l2Text,l2);
                            l2.textProperty().bind(gameState.scoreProperty().asString());
                            Label l3Text = new Label("Time (seconds) : ");
                            Label l3 = new Label();
                            HBox myHbox3 = new HBox(l3Text,l3);
                            l3.textProperty().bind(gameState.timeSecondsProperty().asString());
                            HBox myHboxContent = new HBox(myHbox1,myHbox2,myHbox3);
                            myHboxContent.setSpacing(5);
                            setGraphic(myHboxContent);
                        } else {
                            textProperty().unbind();
                            setText("");
                            setGraphic(null);
                        }
                        setStyle("-fx-background-color: transparent;");
                    }
                }
        );
    }

    public void startNewGame(){
        try{
            GameManager gameManager = new GameManager(new importMap(1216 ,608));
            manager.setGameManager(gameManager);
            gameManager.setDrawMap(new DrawMap(gameManager.getGameMap()));
            FXMLLoader loader = new FXMLLoader(Navigator.GAMEUI);
            GridPane gamePane = new GridPane();
            //Stack Pane coeur
            ImageView imCoeur = new ImageView(new Image(String.valueOf(getClass().getResource("/coeur.PNG").toURI().toURL())));
            imCoeur.setFitHeight(50);
            imCoeur.setFitWidth(50);
            Text liveText = new Text();
            liveText.textProperty().bind(gameManager.getGame().livesProperty().asString());
            StackPane stackPaneCoeur = new StackPane(imCoeur,liveText);
            stackPaneCoeur.setStyle("-fx-font-size: 15");
            //Group tilemap
            tilemapGroup = new Group();
            tilemapGroup.getChildren().addAll(gameManager.getDrawMap(),stackPaneCoeur);
            gamePane.add(tilemapGroup,0,0);
            //Hbox boutons game
            HBox gameUI = loader.load(Navigator.GAMEUI.openStream());
            gamePane.add(gameUI,0,1);
            gameController = loader.getController();
            gameController.setGameManager(gameManager);
            gameController.setScene(Navigator.getStage().getScene());

            ScreenController.addScreen("game", gamePane);
            ScreenController.activate("game");

            listenerOnChangedVictoryAndGameOver();
            listenerOnChangedTowersAndMonsters();
            gameManager.start();
        }catch (IOException | URISyntaxException ex){ex.printStackTrace();}
    }

    private void listenerOnChangedVictoryAndGameOver(){
        GameManager gameManager = manager.getGameManager();
        gameManager.getGame().victoryProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                victory(gameManager.getGame());
            }
        });
        gameManager.getGame().gameOverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                gameOver();
            }
        });
    }

    private void listenerOnChangedTowersAndMonsters(){
        manager.getGameManager().getGame().getPlayerTowers().addListener(new ListChangeListener<Tower>() {
            @Override
            public void onChanged(Change<? extends Tower> change) {
                var listTower = change.getList();
                Tower tower = listTower.get(listTower.size() - 1);
                Coordinate coordinateTower = tower.getCoords();
                createBuildProgressBar(tower);
                InformerProjectiles informerProjectiles = new InformerProjectiles(tower);
            }
        });

        InformerMonsters informerMonsters = new InformerMonsters(manager.getGameManager());
        informerMonsters.monsterProperty().addListener(new ChangeListener<Monster>() {
            @Override
            public void changed(ObservableValue<? extends Monster> observable, Monster oldValue, Monster newValue) {
                tilemapGroup.getChildren().add(newValue.getView());
            }
        });

    }

    public void option(){
        manager.getScoreRanking().getRanking().add(new GameState());
    }


    /*public void createMonster(){
        GameManager gameManager = gameController.getGameManager();
        tilemapGroup.getChildren().add(gameManager.getGame().getMonstersAlive().get(gameManager.getGame().getMonstersAlive().size() - 1).getView());
    }*/

    public void createBuildProgressBar(Tower t) {
        Group g = new Group();
        Coordinate coordinateTower = t.getCoords();
        int seconds = manager.getGameManager().getGame().isSpeed() ? t.getBuildTimeSeconds()/2 : t.getBuildTimeSeconds();

        double xCords = coordinateTower.getTileX()*64;
        double yCords = coordinateTower.getTileY()*64;

        g.setLayoutX(xCords-32);
        g.setLayoutY(yCords-32);
        ProgressBar bar = new ProgressBar();
        Timeline task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(bar.progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(seconds),
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

    public void createProjectiles(){
        Path projectilePath;
        PathTransition animation;
        GameManager gameManager = manager.getGameManager();
        int speedMilis = gameManager.getGame().isSpeed() ? 150 : 300;


        for(Tower tower : gameManager.getGame().getPlayerTowers()){
            for(Projectile projectile : tower.getProjectileList()){
                projectilePath = new Path(new MoveTo(projectile.getStartX() , projectile.getStartY()));
                projectilePath.getElements().add(new LineTo(projectile.getEndX() , projectile.getEndY()));
                animation = new PathTransition(Duration.millis(speedMilis) , projectilePath , projectile);
                animation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        PathTransition finishedAnimation = (PathTransition) actionEvent.getSource();
                        Projectile finishedProjectile = (Projectile) finishedAnimation.getNode();
                        finishedProjectile.setVisible(false);
                        tilemapGroup.getChildren().remove(finishedProjectile);
                        if(finishedProjectile.getTarget().isDead()){
                            Remover.removeMonster(finishedProjectile.getTarget(),gameManager.getGame());
                            Update.updateStates(finishedProjectile.getTarget(),gameManager.getGame());
                        }
                    }
                });
                Platform.runLater(() -> tilemapGroup.getChildren().add(projectile));
                animation.play();
            }
            tower.getProjectileList().clear();
        }
    }


    public void exitGame(){
        System.exit(1);
    }

    public void gameOver() {
        Label l = new Label("Game Over");
        l.setId("labelText");
        Button accueil = new Button("Accueil");
        accueil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    Navigator.mainMenu();
                }
        });
        VBox content = new VBox(l,accueil);
        content.setMaxSize(300,100);
        content.setId("content");
        content.setAlignment(Pos.CENTER);
        VBox sp = new VBox(content);
        sp.setPrefSize(tilemapGroup.getBoundsInParent().getWidth(),tilemapGroup.getBoundsInParent().getHeight());
        sp.setAlignment(Pos.CENTER);
        l.setAlignment(Pos.CENTER);
        tilemapGroup.getChildren().add(sp);
    }

    public void victory(GameState game) {
        manager.getScoreRanking().updateScore(game);
        Label l = new Label("Victory");
        l.setId("labelText");
        Button accueil = new Button("Accueil");
        accueil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Navigator.mainMenu();
            }
        });
        VBox content = new VBox(l,accueil);
        content.setMaxSize(300,100);
        content.setId("content");
        content.setAlignment(Pos.CENTER);
        VBox sp = new VBox(content);
        sp.setPrefSize(tilemapGroup.getBoundsInParent().getWidth(),tilemapGroup.getBoundsInParent().getHeight());
        sp.setAlignment(Pos.CENTER);
        l.setAlignment(Pos.CENTER);
        tilemapGroup.getChildren().add(sp);
    }
}