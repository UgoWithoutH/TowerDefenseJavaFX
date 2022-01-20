package view;


import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.converter.NumberStringConverter;
import model.Manager;
import model.gamelogic.GameManager;
import model.gamelogic.GameState;

import java.io.IOException;
import java.net.URL;

import static java.lang.Thread.sleep;

public class MainMenu {

    @FXML
    private ListView scoreList;
    @FXML
    private TextField pseudoField;
    @FXML
    private TextField nbScores;
    public static final URL GAMEUI = GameManager.class.getResource("/fxml/Game.fxml");
    private Manager manager = ScreenController.getManager();

    /**
     * Initialise fenêtre Menu, Cellfactory
     */
    @FXML
    public void initialize() {
        pseudoField.textProperty().bindBidirectional(manager.pseudoProperty());
        scoreList.setStyle("-fx-background-color: linear-gradient(#bab9b9, #777777);");
        nbScores.textProperty().bindBidirectional(manager.getScoreRanking().numberScoresProperty(),new NumberStringConverter());
        scoreList.itemsProperty().bind(manager.getScoreRanking().rankingProperty());
        scoreList.setCellFactory(__ ->
                new ListCell<GameState>() {
                    @Override
                    protected void updateItem(GameState gameState, boolean empty) {
                        super.updateItem(gameState, empty);
                        if (!empty) {
                            Label l1 = new Label();
                            l1.setStyle("-fx-font-weight: bold;");
                            l1.textProperty().bind(gameState.pseudoProperty());
                            Label l2 = new Label();
                            if (gameState.isVictory()) {
                                l2.setTextFill(Color.GREEN);
                            } else {
                                l2.setTextFill(Color.RED);
                            }
                            l2.textProperty().bind(Bindings.format("Level : %s",gameState.levelProperty().asString()));
                            Label l3 = new Label();
                            l3.textProperty().bind(Bindings.format("Score : %s",gameState.scoreProperty().asString()));
                            Label l4 = new Label();
                            l4.textProperty().bind(Bindings.format("Time (seconds) : %s",gameState.timeSecondsProperty().asString()));
                            HBox myHboxContent = new HBox(l1, l2, l3, l4);
                            myHboxContent.setSpacing(5);
                            setGraphic(myHboxContent);
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

    /**
     * Débute la partie et intialise la vue de la partie
     */
    @FXML
    public void startNewGame() {
        try {
            if(pseudoField.getText() == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention");
                alert.setHeaderText("Incorrect nickname");
                alert.setContentText("Please enter a nickname");
                alert.showAndWait();
                return;
            }
            FXMLLoader loader = new FXMLLoader(GAMEUI);
            assert GAMEUI != null;
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Bouton Exit-Game
     */
    @FXML
    private void exitGame() {
        manager.saveStates();
        System.exit(1);
    }
}