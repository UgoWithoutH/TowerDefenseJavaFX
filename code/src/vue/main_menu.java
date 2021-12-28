package vue;


import game_logic.GameManager;
import game_logic.GameViewLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import model.Map.importMap;
import update.DrawMap;

import java.io.IOException;

public class main_menu implements GameViewLogic {


    private  Scene gameScene;
    private vue.game gameController;
    private Group tilemapGroup;


    public void startNewGame(){
        try{
            GameManager gameManager = new GameManager();
            gameManager.initialize(this, new importMap(1280 ,800));
            DrawMap dr = new DrawMap(gameManager.getGameMap());
            FXMLLoader loader = new FXMLLoader(Navigator.GAMEUI);
            StackPane gamePane = new StackPane();
            tilemapGroup = new Group();
            tilemapGroup.getChildren().add(dr);
            gamePane.getChildren().add(tilemapGroup);

            // Opens stream to get controller reference
            Node gameUI = (Node) loader.load(Navigator.GAMEUI.openStream());
            gamePane.getChildren().add(gameUI);
            gameScene = new Scene(gamePane);
            gameScene.getStylesheets().add(GameManager.class.getResource("/FXML/gamestyle.css").toExternalForm());
            gameController = loader.getController();
            gameController.setGameManager(gameManager);

            Navigator.stage.setScene(gameScene);
        }catch (IOException ex){ex.printStackTrace();}
    }

    public void createMonster(int health){
        GameManager gameManager = gameController.getGameManager();
        tilemapGroup.getChildren().add(gameManager.getGame().getMonstersAlive().get(gameManager.getGame().getMonstersAlive().size() - 1).getView());
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