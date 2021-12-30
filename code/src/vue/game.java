package vue;

import boucleJeu.Boucle;
import game_logic.GameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class game {

    @FXML
    private Label textScore;

    @FXML
    private Button buytower;

    @FXML
    private Button pauseRestart;

    @FXML
    private Label coins;

    private GameManager gameManager;

    private Scene scene;

    private boolean constructTowers = false;

    @FXML
    public void initialize(){
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
                if(constructTowers){
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
        boucle.setMilis(boucle.getMilis()/2);
    }

    public synchronized void pauseOrRestart(ActionEvent actionEvent) throws InterruptedException {//test
        Boucle boucle = gameManager.getBoucle();
        Thread threadBoucle = gameManager.getBoucleThread();

        if(!gameManager.freeze){
            gameManager.freeze = true;
        }
        else{
            gameManager.freeze = false;
            synchronized (this){
                notifyAll();
            }
        }

        /*if (boucle.isRunning()) {
            pauseRestart.setText("Restart");
            boucle.setRunning(false);
            synchronized (threadBoucle){
                threadBoucle.wait();
            }
        } else {
            pauseRestart.setText("Stop");
            boucle.setRunning(true);
            synchronized (threadBoucle){
                threadBoucle.notify();
            }
        }*/
        //ne marche pas
    }
}
