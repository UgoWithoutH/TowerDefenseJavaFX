package vue;

import game_logic.GameManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class game {

    @FXML
    private Label textScore;

    @FXML
    private Button buytower;

    private GameManager gameManager;

    private boolean constructTowers = true;

    @FXML
    public void initialize(){
        buytower.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                constructTowers = true;
            }
        });
    }

    public void setGameManager(GameManager gameManager){
        this.gameManager = gameManager;
        textScore.textProperty().bind(gameManager.game.scoreProperty().asString());
    }

    class buyTower implements EventHandler<MouseEvent> {

        public void handle(MouseEvent me) {
            System.out.println(me);
        }
    }

}
