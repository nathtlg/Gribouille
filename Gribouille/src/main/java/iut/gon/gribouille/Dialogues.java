package iut.gon.gribouille;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Dialogues {

    public static boolean confirmation() {

        ButtonType oui = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType non = new ButtonType("Non", ButtonBar.ButtonData.NO);

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Voulez-vous vraiment quitter ?",
                oui,
                non
        );

        alert.setTitle("Confirmation de fermeture");
        alert.setHeaderText("Quitter l'application ?");

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == oui;
    }
}