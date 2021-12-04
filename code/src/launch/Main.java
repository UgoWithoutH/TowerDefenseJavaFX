package launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {

            Parent root = (Parent) FXMLLoader.load(getClass().getResource("/FXML/index.fxml"));
            Scene scene = new Scene(root, 1000, 800);
            stage.setScene(scene);

            stage.setTitle("JavaFX");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}