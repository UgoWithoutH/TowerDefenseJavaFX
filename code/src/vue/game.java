package vue;

import game_logic.GameManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class game {

    @FXML
    private Label textScore;

    private GameManager gameManager;

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
