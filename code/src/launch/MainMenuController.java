package launch;


import game_logic.GameManager;

import java.io.IOException;

public class MainMenuController {


    public void startNewGame(){
        try{
            GameManager gameManager = new GameManager();
            gameManager.initialize();
        }catch (IOException ex){ex.printStackTrace();}
    }


    public void exitGame(){
        System.exit(1);

    }
}