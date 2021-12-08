package game_logic;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class GameController {

    private GameManager gameManager;

    public void setGameManager(GameManager gameManager){
        this.gameManager = gameManager;
    }



    class buyTower implements EventHandler<MouseEvent> {

        public void handle(MouseEvent me) {
            System.out.println(me);
        }
    }

}
