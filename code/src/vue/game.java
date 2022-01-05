package vue;

import boucleJeu.Boucle;
import game_logic.GameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class game {

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
    @FXML
    private Label coeur;

    private GameManager gameManager;

    private Scene scene;

    private boolean constructTowers = false;

    public void initialize(){
        try {
            ImageView im = new ImageView(new Image(String.valueOf(getClass().getResource("/tower.PNG").toURI().toURL())));
            im.setFitHeight(20);
            im.setFitWidth(20);
            buytower.setGraphic(im);
            ImageView imCoeur = new ImageView(new Image(String.valueOf(getClass().getResource("/coeur.PNG").toURI().toURL())));
            imCoeur.setFitHeight(20);
            imCoeur.setFitWidth(20);
            coeur.setGraphic(imCoeur);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public GameManager getGameManager(){
        return gameManager;
    }

    public void setGameManager(GameManager gameManager){
        this.gameManager = gameManager;
        textScore.textProperty().bind(gameManager.getGame().scoreProperty().asString());
        coins.textProperty().bind(gameManager.getGame().coinsProperty().asString());
    }

    public void setScene(Scene scene){
        this.scene = scene;
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(constructTowers && !gameManager.getGame().isGameOver()){
                    gameManager.buyTower(event.getX(),event.getY());
                    constructTowers = false;
                }
            }
        });
    }

    public void buyTower(ActionEvent actionEvent) {
        constructTowers = true;
    }

    public void Speed(ActionEvent actionEvent) {
        Boucle boucle = gameManager.getBoucle();
        if(!boucle.isSpeed()){
            speed.setText("Normal");
            boucle.setSpeed(true);
            boucle.setMilis(boucle.getMilis()/2);
        }
        else{
            speed.setText("X2");
            boucle.setSpeed(false);
            boucle.setMilis(boucle.getMilis()*2);
        }
    }

    public synchronized void pauseOrRestart(ActionEvent actionEvent) throws InterruptedException {//test
        Boucle boucle = gameManager.getBoucle();
        Thread threadBoucle = gameManager.getBoucleThread();

        if(gameManager.getBoucle().isRunning()){
            pauseRestart.setText("Restart");
            boucle.setRunning(false);
        }
        else{
            pauseRestart.setText("Stop");
            boucle.setRunning(true);
            gameManager.start();
        }
    }
}
