package vue;


import game_logic.GameManager;

import java.io.IOException;

public class main_menu {


    public void startNewGame(){
        try{
            GameManager gameManager = new GameManager();
            gameManager.initialize();
        }catch (IOException ex){ex.printStackTrace();}
    }

    public void startNewGameConsole(){
        try{
            GameManager gameManager = new GameManager();
            gameManager.initializeConsole();
        }catch (IOException ex){
            System.out.println("Erreur startNewGameConsole" + ex);
        }
    }

    public void exitGame(){
        System.exit(1);

    }
}