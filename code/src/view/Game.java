package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.gameloop.Loop;
import model.gamelogic.GameManager;
import model.gamelogic.action.IBuyer;
import model.gamelogic.action.tower.BuyerTower;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Objects;

public class Game {

    @FXML
    private Label textScore;
    @FXML
    private Button buytower;
    @FXML
    private Button pauseRestart;
    @FXML
    private Label coins;
    @FXML
    private Button speed;
    private GameManager gameManager;
    private Scene scene;
    private boolean constructTowers = false;

    @FXML
    public void initialize() {
        try {
            ImageView im = new ImageView(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/images/tower.PNG")).toURI().toURL())));
            im.setFitHeight(20);
            im.setFitWidth(20);
            buytower.setGraphic(im);
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
        textScore.textProperty().bind(gameManager.getGame().scoreProperty().asString());
        coins.textProperty().bind(gameManager.getGame().coinsProperty().asString());
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (constructTowers && gameManager.getLoop().isRunning()) {
                    IBuyer buyer = new BuyerTower(gameManager.getGame(),gameManager.getGameMap(),gameManager.getDrawMap());
                    buyer.buy(event.getX(), event.getY());
                    constructTowers = true;
                }
            }
        });
    }

    /**
     * Button ON/OFF Buy Tower
     *
     * @param actionEvent
     */
    @FXML
    private void buyTower(ActionEvent actionEvent) {
        constructTowers = true;
    }

    @FXML
    private void speed(ActionEvent actionEvent) {
        Loop boucle = gameManager.getLoop();
        if(!gameManager.getGame().isSpeed()){
            speed.setText("X1");
            gameManager.getGame().setSpeed(true);
            boucle.setMillis(boucle.getMillis()/2);
        }
        else{
            speed.setText("X2");
            gameManager.getGame().setSpeed(false);
            boucle.setMillis(boucle.getMillis()*2);
        }
    }

    @FXML
    private void pauseOrRestart(ActionEvent actionEvent){
        if(gameManager.getLoop().isRunning()) {
            pauseRestart.setText("Restart");
            gameManager.getLoop().setRunning(false);
        } else {
            pauseRestart.setText("Stop");
            gameManager.getLoop().setRunning(true);
            gameManager.start();
        }
    }

    @FXML
    public void giveUp(ActionEvent actionEvent) {
        gameManager.getLoop().setRunning(false);

        ScreenController.getManager().getScoreRanking().updateRanking(ScreenController.getManager().getGameManager().getGame());
        ScreenController.activate("setup");
    }
}
