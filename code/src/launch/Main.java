package launch;

import javafx.fxml.FXMLLoader;
import model.Manager;
import model.ScoreRanking;
import view.Navigator;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ScreenController;

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
        //Navigator.setManager(new Manager(new ScoreRanking()));
        Navigator.mainMenu();
        stage.show();

        ScreenController screenController = new ScreenController(stage.getScene());
        screenController.addScreen("option", FXMLLoader.load(Navigator.OPTIONUI));
        screenController.addScreen("game", FXMLLoader.load(Navigator.GAMEUI));
        screenController.addScreen("menu", FXMLLoader.load(Navigator.MENUUI));

    }

    public static void main(String[] args) {
        launch(args);
    }
}