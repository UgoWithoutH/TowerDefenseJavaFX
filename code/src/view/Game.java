package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Label level;
    @FXML
    private Button speed;
    private GameManager gameManager;
    private Scene scene;
    private boolean constructTowers = false;

    /**
     * Initialize la vue d'une partie
     */
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
        textScore.textProperty().bind(Bindings.format("Score : %s",gameManager.getGame().scoreProperty().asString()));
        coins.textProperty().bind(Bindings.format("Coins : %s",gameManager.getGame().coinsProperty().asString()));
        level.textProperty().bind(Bindings.format("Level : %s",gameManager.getGame().levelProperty().asString()));
    }

    /**
     * Recupere l'event, la position du click pour construire une Tower
     * @param scene
     */
    public void setScene(Scene scene) {
        this.scene = scene;
        scene.setOnMouseClicked(event -> {
                if (constructTowers && gameManager.getLoop().isRunning()) {
                    IBuyer buyer = new BuyerTower(gameManager.getGame(),gameManager.getGameMap(),gameManager.getDrawMap());
                    buyer.buy(event.getX(), event.getY());
                    constructTowers = true;
                }
            }
        );
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

    /**
     * Bouton Pause/Start
     */
    @FXML
    private void pauseOrRestart(){
        if(gameManager.getLoop().isRunning()) {
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
        gameManager.getLoop().setRunning(false);
        ScreenController.activate("setup");
    }
}
