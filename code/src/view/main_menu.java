package view;


import game_logic.GameManager;
import game_logic.GameViewLogic;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import model.Map.importMap;
import model.characters.Projectile;
import model.characters.tower.Tower;
import update.DrawMap;

import java.io.IOException;
import java.net.URISyntaxException;

public class main_menu implements GameViewLogic {


    private  Scene gameScene;
    private view.game gameController;
    private Group tilemapGroup;
    private GameManager gameManager;


    public void startNewGame() throws URISyntaxException {
        try{
            gameManager = new GameManager(this, new importMap(1216 ,608));
            gameManager.setDrawMap(new DrawMap(gameManager.getGameMap()));
            FXMLLoader loader = new FXMLLoader(Navigator.GAMEUI);
            GridPane gamePane = new GridPane();
            tilemapGroup = new Group();
            tilemapGroup.getChildren().add(gameManager.getDrawMap());
            gameManager.getBasicTowerFactory().setTilemapGroup(tilemapGroup);
            gamePane.add(tilemapGroup,0,0);
            HBox gameUI = loader.load(Navigator.GAMEUI.openStream());
            gamePane.getStylesheets().add(GameManager.class.getResource("/FXML/menustyle.css").toExternalForm());
            gamePane.add(gameUI,0,1);
            gameScene = new Scene(gamePane);
            gameController = loader.getController();
            gameController.setGameManager(gameManager);
            gameController.setScene(gameScene);
            Navigator.getStage().setScene(gameScene);
            gameManager.start();
        }catch (IOException ex){ex.printStackTrace();}
    }

    public void createMonster(int health){
        GameManager gameManager = gameController.getGameManager();
        tilemapGroup.getChildren().add(gameManager.getGame().getMonstersAlive().get(gameManager.getGame().getMonstersAlive().size() - 1).getView());
    }


    public void exitGame(){
        System.exit(1);
    }

    @Override
    public void gameOver() {
        Label l = new Label("Game Over");
        l.setId("gameOverLabel");
        Button accueil = new Button("Accueil");
        accueil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    Navigator.mainMenu();
                }
        });
        VBox content = new VBox(l,accueil);
        content.setMaxSize(300,100);
        content.setId("contentGameOver");
        content.setAlignment(Pos.CENTER);
        VBox sp = new VBox(content);
        sp.setPrefSize(tilemapGroup.getBoundsInParent().getWidth(),tilemapGroup.getBoundsInParent().getHeight());
        sp.setAlignment(Pos.CENTER);
        l.setAlignment(Pos.CENTER);
        tilemapGroup.getChildren().add(sp);
    }
}