package launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.Manager;
import model.ScoreRanking;
import model.game_logic.GameManager;
import view.Navigator;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ScreenController;

import java.util.HashMap;

public class Main extends Application {

    public final static int RESOLUTION_X = 1216;
    public final static int RESOLUTION_Y = 672;

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Tower Defense");
        stage.setWidth(RESOLUTION_X);
        stage.setHeight(RESOLUTION_Y);
        stage.setResizable(false);
        Navigator.setStage(stage);
        ScreenController.setStage(stage);
        Navigator.setManager(new Manager(new ScoreRanking()));
        Navigator.mainMenu();
        stage.show();
    }

    @Override
    public void stop(){
        System.out.println("Window is closing");
    }

    public static void main(String[] args) {
        launch(args);
    }
}