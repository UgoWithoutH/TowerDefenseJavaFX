package launch;

import vue.main_vue;
import vue.main_menu;
import vue.Navigator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

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
        Navigator.mainMenu();
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
        /*
        main_menu start = new main_menu();
        start.startNewGameConsole();
        */
    }
}