package view;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Coordinate;
import model.Manager;
import model.characters.tower.Tower;
import model.game_logic.GameManager;
import model.game_logic.GameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import model.Map.importMap;
import model.Map.update.DrawMap;
import view.creators.CreatorMonsters;
import view.creators.CreatorProjectiles;

import java.io.IOException;
import java.net.URISyntaxException;

public class main_menu {

    private view.game gameController;
    private Group tilemapGroup;
    private Manager manager = ScreenController.getManager();
    @FXML
    private ListView scoreList;

    @FXML
    public void initialize() {
        scoreList.setStyle("-fx-background-color: linear-gradient(#bab9b9, #777777);");
        scoreList.itemsProperty().bind(manager.getScoreRanking().rankingProperty());
        scoreList.setCellFactory(__ ->
                new ListCell<GameState>() {
                    @Override
                    protected void updateItem(GameState gameState, boolean empty) {
                        super.updateItem(gameState, empty);
                        if (!empty) {
                            Label l1Text = new Label("Level : ");
                            Label l1 = new Label();

                            if (gameState.isVictory()) {
                                l1.setTextFill(Color.GREEN);
                            } else {
                                l1.setTextFill(Color.RED);
                            }

                            HBox myHbox1 = new HBox(l1Text, l1);
                            l1.textProperty().bind(gameState.levelProperty().asString());
                            Label l2Text = new Label("Score : ");
                            Label l2 = new Label();
                            HBox myHbox2 = new HBox(l2Text, l2);
                            l2.textProperty().bind(gameState.scoreProperty().asString());
                            Label l3Text = new Label("Time (seconds) : ");
                            Label l3 = new Label();
                            HBox myHbox3 = new HBox(l3Text, l3);
                            l3.textProperty().bind(gameState.timeSecondsProperty().asString());
                            HBox myHboxContent = new HBox(myHbox1, myHbox2, myHbox3);
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

    public void startNewGame() {
        try {
            GameManager gameManager = new GameManager(new importMap(1216, 608));
            manager.setGameManager(gameManager);
            gameManager.setDrawMap(new DrawMap(gameManager.getGameMap()));
            FXMLLoader loader = new FXMLLoader(Navigator.GAMEUI);
            GridPane gamePane = new GridPane();
            //Stack Pane coeur
            ImageView imCoeur = new ImageView(new Image(String.valueOf(getClass().getResource("/images/coeur.PNG").toURI().toURL())));
            imCoeur.setFitHeight(50);
            imCoeur.setFitWidth(50);
            Text liveText = new Text();
            liveText.textProperty().bind(gameManager.getGame().livesProperty().asString());
            StackPane stackPaneCoeur = new StackPane(imCoeur, liveText);
            stackPaneCoeur.setStyle("-fx-font-size: 15");
            //Group tilemap
            tilemapGroup = new Group();
            tilemapGroup.getChildren().addAll(gameManager.getDrawMap(), stackPaneCoeur);
            gamePane.add(tilemapGroup, 0, 0);
            //Hbox boutons game
            HBox gameUI = loader.load(Navigator.GAMEUI.openStream());
            gamePane.add(gameUI, 0, 1);
            gameController = loader.getController();
            gameController.setGameManager(gameManager);
            gameController.setScene(ScreenController.getStage().getScene());

            ScreenController.addScreen("game", gamePane);
            ScreenController.activate("game");

            listenerOnChangedVictoryAndGameOver();
            createCreators();
            gameManager.start();
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    public void option() {}

    private void listenerOnChangedVictoryAndGameOver() {
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
                gameOver(gameManager.getGame());
            }
        });
    }

    private void createCreators() {
        manager.getGameManager().getGame().getPlayerTowers().addListener(new ListChangeListener<Tower>() {
            @Override
            public void onChanged(Change<? extends Tower> change) {
                var listTower = change.getList();
                Tower tower = listTower.get(listTower.size() - 1);
                Coordinate coordinateTower = tower.getCoords();
                createBuildProgressBar(tower);
                new CreatorProjectiles(manager.getGameManager(),tower,tilemapGroup);
            }
        });
        new CreatorMonsters(manager.getGameManager(),tilemapGroup);
    }

    /** todo
     *   lorsque la boucle est arrété il faut STOP le build
     *
     * @param t
     */
    public void createBuildProgressBar(Tower t) {
        Group g = new Group();
        Coordinate coordinateTower = t.getCoords();
        int seconds = manager.getGameManager().getGame().isSpeed() ? t.getBuildTimeSeconds() / 2 : t.getBuildTimeSeconds();

        double xCords = coordinateTower.getTileX() * 64;
        double yCords = coordinateTower.getTileY() * 64;

        g.setLayoutX(xCords - 32);
        g.setLayoutY(yCords - 32);
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


    public void exitGame(){
        System.exit(1);
    }

    public void gameOver(GameState game) {
        manager.getScoreRanking().updateRanking(game);
        Label l = new Label("Game Over");
        l.setId("labelText");
        Button accueil = new Button("Accueil");
        accueil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    Navigator.affichageMenu();
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
        manager.getScoreRanking().updateRanking(game);
        Label l = new Label("Victory");
        l.setId("labelText");
        Button accueil = new Button("Accueil");
        accueil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Navigator.affichageMenu();
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