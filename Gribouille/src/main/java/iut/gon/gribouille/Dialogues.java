package iut.gon.gribouille;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Dialogues {

    public static boolean confirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de fermeture");
        alert.setHeaderText("Quitter l'application ?");
        alert.setContentText("Voulez-vous vraiment quitter ?");

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
