package vue;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class main_vue {

    /** Switch de vue par holder FXML (fx:id). */
    @FXML
    public StackPane vistaHolder;

    /**
     * Remplace la vue affichée par une vue sélectionée
     *
     * @param node vue a remplacée
     */
    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }

}