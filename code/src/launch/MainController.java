package launch;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController {

    /** Switch de vue par holder FXML (fx:id). */
    @FXML
    private StackPane vistaHolder;

    /**
     * Remplace la vue affichée par une vue sélectionée
     *
     * @param node vue a remplacée
     */
    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }

}