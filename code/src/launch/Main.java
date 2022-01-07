package launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import model.Manager;
import model.ScoreRanking;
import view.Navigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public final static int RESOLUTION_X = 1216;
    public final static int RESOLUTION_Y = 672;

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Tower Defense");
        stage.setWidth(RESOLUTION_X);
        stage.setHeight(RESOLUTION_Y);
        stage.setResizable(false);
        Navigator.setManager(new Manager(new ScoreRanking()));
        var root = FXMLLoader.load(getClass().getResource("/FXML/main_menu.fxml"));
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.getScene().getStylesheets().setAll(Navigator.class.getResource("/FXML/menustyle.css").toExternalForm());
        Navigator.setStage(stage);
        //Navigator.mainMenu();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}