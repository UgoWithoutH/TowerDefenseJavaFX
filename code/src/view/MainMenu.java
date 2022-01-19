package view;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;
import model.Coordinate;
import model.Manager;
import model.characters.tower.Tower;
import model.gamelogic.GameManager;
import model.gamelogic.GameState;
import model.gamelogic.map.importMap;
import view.map.DrawMap;
import view.creators.CreatorMonsters;
import view.creators.CreatorProjectiles;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class MainMenu {

    private Game gameController;
    private Group tilemapGroup;
    private Manager manager = ScreenController.getManager();
    public static final URL GAMEUI = GameManager.class.getResource("/fxml/Game.fxml");
    @FXML
    private ListView scoreList;
    @FXML
    private TextField pseudoField;
    @FXML
    private TextField nbScores;

    @FXML
    public void initialize() {
        pseudoField.textProperty().bindBidirectional(manager.pseudoProperty());
        scoreList.setStyle("-fx-background-color: linear-gradient(#bab9b9, #777777);");
        nbScores.textProperty().bindBidirectional(manager.getScoreRanking().numberScoresProperty(),new NumberStringConverter());
        scoreList.itemsProperty().bind(manager.getScoreRanking().rankingProperty());
        scoreList.setCellFactory(__ ->
                new ListCell<GameState>() {
                    @Override
                    protected void updateItem(GameState gameState, boolean empty) {
                        super.updateItem(gameState, empty);
                        if (!empty) {
                            Label l1 = new Label();
                            l1.setStyle("-fx-font-weight: bold;");
                            l1.textProperty().bind(gameState.pseudoProperty());
                            Label l2 = new Label();
                            if (gameState.isVictory()) {
                                l2.setTextFill(Color.GREEN);
                            } else {
                                l2.setTextFill(Color.RED);
                            }
                            l2.textProperty().bind(Bindings.format("Level : %s",gameState.levelProperty().asString()));
                            Label l3 = new Label();
                            l3.textProperty().bind(Bindings.format("Score : %s",gameState.scoreProperty().asString()));
                            Label l4 = new Label();
                            l4.textProperty().bind(Bindings.format("Time (seconds) : %s",gameState.timeSecondsProperty().asString()));
                            HBox myHboxContent = new HBox(l1, l2, l3, l4);
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

    /**
     * Start Window and Game
     */
    public void startNewGame() {
        try {
            if(pseudoField.getText() == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention");
                alert.setHeaderText("Incorrect nickname");
                alert.setContentText("Please enter a nickname");
                alert.showAndWait();
                return;
            }
            GameManager gameManager = new GameManager(manager.getPseudo(),new importMap(1216, 608));
            manager.setGameManager(gameManager);
            gameManager.setDrawMap(new DrawMap(gameManager.getGameMap()));
            FXMLLoader loader = new FXMLLoader(GAMEUI);
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
            HBox gameUI = loader.load(GAMEUI.openStream());
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
                gameOverOrVictory(gameManager.getGame());
            }
        });
        gameManager.getGame().gameOverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                gameOverOrVictory(gameManager.getGame());
            }
        });
    }

    private void createCreators() {
        manager.getGameManager().getGame().getPlayerTowers().addListener(new ListChangeListener<Tower>() {
            @Override
            public void onChanged(Change<? extends Tower> change) {
                var listTower = change.getList();
                Tower tower = listTower.get(listTower.size() - 1);
                Coordinate coordinateTower = tower.getCoordinate();
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
        Coordinate coordinateTower = t.getCoordinate();
        int seconds = manager.getGameManager().getGame().isSpeed() ? t.getBuildTimeSeconds() / 2 : t.getBuildTimeSeconds();

        double xCords = coordinateTower.getX() * 64;
        double yCords = coordinateTower.getY() * 64;

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
        task.setOnFinished(event -> bar.setVisible(false));
        g.getChildren().add(bar);
        tilemapGroup.getChildren().add(g);
        task.playFromStart();
    }


    public void exitGame() {
        System.exit(1);
    }

    /**
     * Window GameOver
     *
     * @param game
     */
    public void gameOverOrVictory(GameState game) {
        manager.getScoreRanking().updateRanking(game);
        Label l = new Label();
        if(game.isVictory()){
            l.setText("Victory");
            l.setTextFill(Color.GREEN);
        }
        else{
            l.setText("GameOver");
            l.setTextFill(Color.RED);
        }
        l.setId("labelText");
        Button accueil = new Button("Accueil");
        accueil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ScreenController.activate("setup");
            }
        });
        VBox content = new VBox(l,accueil);
        content.setMaxSize(300, 100);
        content.setId("content");
        content.setAlignment(Pos.CENTER);
        VBox sp = new VBox(content);
        sp.setPrefSize(tilemapGroup.getBoundsInParent().getWidth(), tilemapGroup.getBoundsInParent().getHeight());
        sp.setAlignment(Pos.CENTER);
        l.setAlignment(Pos.CENTER);
        tilemapGroup.getChildren().add(sp);
    }



}