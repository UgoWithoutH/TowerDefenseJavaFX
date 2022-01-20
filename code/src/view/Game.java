package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Coordinate;
import model.Manager;
import model.characters.tower.Tower;
import model.gamelogic.GameState;
import model.gamelogic.map.ImportMap;
import model.gameloop.Loop;
import model.gamelogic.GameManager;
import model.gamelogic.action.IBuyer;
import model.gamelogic.action.tower.BuyerTower;
import view.creators.CreatorMonsters;
import view.creators.CreatorProjectiles;
import view.map.DrawMap;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class Game {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label textScore;
    @FXML
    private Button buytower;
    @FXML
    private Button pauseRestart;
    @FXML
    private Label coins;
    @FXML
    private Label level;
    @FXML
    private Button speed;
    private boolean constructTowers = false;
    private Manager manager = ScreenController.getManager();
    private Group tileMapGroup;

    /**
     * initialise des éléments de la vue de la partie
     */
    @FXML
    public void initialize() {
        try {
            GameManager gameManager = new GameManager(manager.getPseudo(), new ImportMap(1216, 608));
            manager.setGameManager(gameManager);
            DrawMap drawMap = new DrawMap(gameManager.getGameMap());

            //Stack Pane coeur
            ImageView imCoeur = null;
            imCoeur = new ImageView(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/images/coeur.PNG")).toURI().toURL())));
            imCoeur.setFitHeight(50);
            imCoeur.setFitWidth(50);
            Text liveText = new Text();
            liveText.textProperty().bind(gameManager.getGame().livesProperty().asString());
            StackPane stackPaneCoeur = new StackPane(imCoeur, liveText);
            stackPaneCoeur.setStyle("-fx-font-size: 15");

            //Binding informations sur la partie
            textScore.textProperty().bind(Bindings.format("Score : %s",gameManager.getGame().scoreProperty().asString()));
            coins.textProperty().bind(Bindings.format("Coins : %s",gameManager.getGame().coinsProperty().asString()));
            level.textProperty().bind(Bindings.format("Level : %s",gameManager.getGame().levelProperty().asString()));

            //Bouton tower
            ImageView im = new ImageView(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/images/tower.PNG")).toURI().toURL())));
            im.setFitHeight(20);
            im.setFitWidth(20);
            buytower.setGraphic(im);

            //compteur début de partie
            Label counter = new Label();
            counter.setStyle("-fx-font-size: 100");
            counter.setAlignment(Pos.CENTER);
            StackPane stackPaneCounter = new StackPane(counter);
            stackPaneCounter.setPrefSize(gameManager.getGameMap().getResolutionWidth(), gameManager.getGameMap().getResolutionHeight());

            //Group tilemap
            tileMapGroup = new Group();
            tileMapGroup.getChildren().addAll(drawMap, stackPaneCoeur, stackPaneCounter);
            gridPane.add(tileMapGroup, 0, 0);

            ScreenController.addScreen("game", gridPane);
            ScreenController.activate("game");

            listenerOnChangedVictoryAndGameOver();
            createCreators();

            //Evénement de click -> build tower
            ScreenController.getStage().getScene().setOnMouseClicked(event -> {
                        if (constructTowers && gameManager.getLoop().isRunning()) {
                            IBuyer buyer = new BuyerTower(gameManager.getGame(), gameManager.getGameMap());
                            if(buyer.buy(event.getX(), event.getY())){
                                drawMap.draw();
                            }
                            constructTowers = false;
                        }
                    }
            );

            //déclenchement compteur
            animationTime(counter, gameManager);
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    /**
     * Animation du compteur de début de partie
     *
     * @param counter
     * @param gameManager
     */
    private void animationTime(Label counter, GameManager gameManager) {
        Thread thread = new Thread(() -> {
            try {
                sleep(500);
                for (int i = 3; i >= 0; i--) {
                    final int tmp = i;
                    Platform.runLater(() -> counter.setText(String.valueOf(tmp)));
                    sleep(1000);
                }
                Timeline task = new Timeline(
                        new KeyFrame(
                                Duration.ZERO,
                                new KeyValue(counter.opacityProperty(), 1)
                        ),
                        new KeyFrame(
                                Duration.seconds(1),
                                new KeyValue(counter.opacityProperty(), 0.0)
                        )
                );
                task.playFromStart();
                if (gameManager.getGame().getLevel() == 1) {
                    gameManager.start();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    /**
     * Ajoute les listener sur la victoire ou la gameOver de la partie
     */
    private void listenerOnChangedVictoryAndGameOver() {
        GameManager gameManager = manager.getGameManager();
        gameManager.getGame().victoryProperty().addListener((observableValue, aBoolean, t1) -> gameOverOrVictory(gameManager.getGame()));
        gameManager.getGame().gameOverProperty().addListener((observableValue, aBoolean, t1) -> gameOverOrVictory(gameManager.getGame()));
    }

    /**
     * Listener Projectile et des Monster
     */
    private void createCreators() {
        manager.getGameManager().getGame().getPlayerTowers().addListener((ListChangeListener<Tower>) change -> {
            var listTower = change.getList();
            Tower tower = listTower.get(listTower.size() - 1);
            createBuildProgressBar(tower);
            new CreatorProjectiles(manager.getGameManager(), tower, tileMapGroup);
        });
        new CreatorMonsters(manager.getGameManager(), tileMapGroup);
    }

    /**
     * Creer une progress bar de Tower lors de construction d'une Tower
     *
     * @param tower Tower
     */
    private void createBuildProgressBar(Tower tower) {
        Group g = new Group();
        Coordinate coordinateTower = tower.getCoordinate();
        int seconds = manager.getGameManager().getGame().isSpeed() ? tower.getBuildTimeSeconds() / 2 : tower.getBuildTimeSeconds();

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
        tileMapGroup.getChildren().add(g);
        task.playFromStart();
    }


    /**
     * Fenêtre GameOver
     *
     * @param game
     */
    private void gameOverOrVictory(GameState game) {
        manager.getScoreRanking().updateRanking(game);
        Label l = new Label();
        if (game.isVictory()) {
            l.setText("Victory");
            l.setTextFill(Color.GREEN);
        } else {
            l.setText("GameOver");
            l.setTextFill(Color.RED);
        }
        l.setId("labelText");
        Button accueil = new Button("Accueil");
        accueil.setOnAction(event -> ScreenController.activate("setup"));
        VBox content = new VBox(l, accueil);
        content.setMaxSize(300, 100);
        content.setId("content");
        content.setAlignment(Pos.CENTER);
        VBox sp = new VBox(content);
        sp.setPrefSize(tileMapGroup.getBoundsInParent().getWidth(), tileMapGroup.getBoundsInParent().getHeight());
        sp.setAlignment(Pos.CENTER);
        l.setAlignment(Pos.CENTER);
        tileMapGroup.getChildren().add(sp);
    }

    /**
     * Button ON/OFF Buy Tower
     */
    @FXML
    private void buyTower() {
        constructTowers = true;
    }

    /**
     * Bouton d'augmentation de la vitesse
     */
    @FXML
    private void speed() {
        GameManager gameManager = manager.getGameManager();
        Loop boucle = gameManager.getLoop();
        if (!gameManager.getGame().isSpeed()) {
            speed.setText("X1");
            gameManager.getGame().setSpeed(true);
            boucle.setMillis(boucle.getMillis() / 2);
        } else {
            speed.setText("X2");
            gameManager.getGame().setSpeed(false);
            boucle.setMillis(boucle.getMillis() * 2);
        }
    }

    /**
     * Bouton Pause/Start
     */
    @FXML
    private void pauseOrRestart() {
        GameManager gameManager = manager.getGameManager();
        if (gameManager.getLoop().isRunning()) {
            pauseRestart.setText("Restart");
            gameManager.getLoop().setRunning(false);
        } else {
            pauseRestart.setText("Stop");
            gameManager.getLoop().setRunning(true);
            gameManager.start();
        }
    }

    /**
     * Bouton Give-UP
     */
    @FXML
    private void giveUp() {
        manager.getGameManager().getLoop().setRunning(false);
        ScreenController.activate("setup");
    }
}
