package view;


import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Manager;
import model.ScoreRanking;
import model.game_logic.GameManager;
import model.game_logic.GameState;
import model.game_logic.GameViewLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Map.importMap;
import model.update.DrawMap;

import java.io.IOException;
import java.net.URISyntaxException;

public class main_menu implements GameViewLogic {


    private  Scene gameScene;
    private view.game gameController;
    private Group tilemapGroup;
    private Manager manager = Navigator.getManager();
    @FXML
    private ListView scoreList;

    public void initialize(){
        scoreList.setStyle("-fx-background-color: transparent;");
        scoreList.itemsProperty().bind(manager.getScoreRanking().rankingProperty());
        scoreList.setCellFactory(__ ->
                new ListCell<GameState>(){
                    @Override
                    protected void updateItem(GameState gameState, boolean empty) {
                        super.updateItem(gameState, empty);
                        if (!empty) {
                            VBox myVbox = new VBox();
                            Label l1Text = new Label("Level : ");
                            Label l1 = new Label();
                            l1.textProperty().bind(gameState.levelProperty().asString());
                            Label l2Text = new Label("Score : ");
                            Label l2 = new Label();
                            l2.textProperty().bind(gameState.scoreProperty().asString());
                            myVbox.getChildren().addAll(l1Text,l1,l2Text,l2);
                            setGraphic(myVbox);
                        } else {
                            textProperty().unbind();
                            setText("");
                            setGraphic(null);
                        }
                        setStyle("-fx-background-color: transparent;");
                    }
                }
        );
    }

    public void startNewGame() throws URISyntaxException {
        try{
            GameManager gameManager = manager.getGameManager();
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
        l.setId("labelText");
        Button accueil = new Button("Accueil");
        accueil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    Navigator.mainMenu();
                }
        });
        VBox content = new VBox(l,accueil);
        content.setMaxSize(300,100);
        content.setId("content");
        content.setAlignment(Pos.CENTER);
        VBox sp = new VBox(content);
        sp.setPrefSize(tilemapGroup.getBoundsInParent().getWidth(),tilemapGroup.getBoundsInParent().getHeight());
        sp.setAlignment(Pos.CENTER);
        l.setAlignment(Pos.CENTER);
        tilemapGroup.getChildren().add(sp);
    }

    @Override
    public void victory(GameState game) {
        manager.getScoreRanking().updateScore(game);
        Label l = new Label("Victory");
        l.setId("labelText");
        Button accueil = new Button("Accueil");
        accueil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Navigator.mainMenu();
            }
        });
        VBox content = new VBox(l,accueil);
        content.setMaxSize(300,100);
        content.setId("content");
        content.setAlignment(Pos.CENTER);
        VBox sp = new VBox(content);
        sp.setPrefSize(tilemapGroup.getBoundsInParent().getWidth(),tilemapGroup.getBoundsInParent().getHeight());
        sp.setAlignment(Pos.CENTER);
        l.setAlignment(Pos.CENTER);
        tilemapGroup.getChildren().add(sp);
    }
}