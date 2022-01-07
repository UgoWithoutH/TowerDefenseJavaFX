package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.boucleJeu.Boucle;
import model.game_logic.GameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private GameManager gameManager;
    private Scene scene;
    private boolean constructTowers = false;

    @FXML
    public void initialize(){
        try {
            ImageView im = new ImageView(new Image(String.valueOf(getClass().getResource("/tower.PNG").toURI().toURL())));
            im.setFitHeight(20);
            im.setFitWidth(20);
            buytower.setGraphic(im);
        } catch (MalformedURLException | URISyntaxException e) {e.printStackTrace();}
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
                if(constructTowers && gameManager.getBoucle().isRunning()){
                    gameManager.buyTower(event.getX(),event.getY());
                    constructTowers = false;
                }
            }
        });
    }
    @FXML
    public void buyTower(ActionEvent actionEvent) {
        constructTowers = true;
    }

    @FXML
    public void Speed(ActionEvent actionEvent) {
        Boucle boucle = gameManager.getBoucle();
        if(!gameManager.getGame().isSpeed()){
            speed.setText("Normal");
            gameManager.getGame().setSpeed(true);
            boucle.setMilis(boucle.getMilis()/2);
        }
        else{
            speed.setText("X2");
            gameManager.getGame().setSpeed(false);
            boucle.setMilis(boucle.getMilis()*2);
        }
    }

    @FXML
    public void pauseOrRestart(ActionEvent actionEvent){
        if(gameManager.getBoucle().isRunning()){
            pauseRestart.setText("Restart");
            gameManager.getBoucle().setRunning(false);
        }
        else{
            pauseRestart.setText("Stop");
            gameManager.getBoucle().setRunning(true);
            gameManager.start();
        }
    }

    public void giveUp(ActionEvent actionEvent) {
        gameManager.getBoucle().setRunning(false);
        Navigator.mainMenu();
    }
}
