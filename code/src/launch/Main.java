package launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import model.Manager;
import model.ScoreRanking;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ScreenController;

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

        new ScreenController(stage);

        /**
         * Premier affichage de fenétre !!
         */
        try {
            stage.setScene(new Scene(FXMLLoader.load((Main.class.getResource("/FXML/main_menu.fxml")))));
            stage.getScene().getStylesheets().setAll(Main.class.getResource("/FXML/menustyle.css").toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }
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