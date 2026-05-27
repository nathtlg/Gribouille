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
    
    public enum ResultatQuitter {
        QUITTER_SANS_SAUVEGARDER,
        QUITTER_EN_SAUVEGARDANT,
        NE_PAS_QUITTER
    }
    
    public static ResultatQuitter dialogueQuitter() {
        ButtonType sauveEtQuitte = new ButtonType("Sauvegarder et quitter", ButtonBar.ButtonData.YES);
        ButtonType quitteSeul    = new ButtonType("Quitter sans sauvegarder", ButtonBar.ButtonData.NO);
        ButtonType annuler       = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
 
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Le dessin a été modifié. Voulez-vous le sauvegarder avant de quitter ?",
                sauveEtQuitte,
                quitteSeul,
                annuler
        );
        alert.setTitle("Quitter");
        alert.setHeaderText("Modifications non sauvegardées");
 
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty()) return ResultatQuitter.NE_PAS_QUITTER;
        if (result.get() == sauveEtQuitte) return ResultatQuitter.QUITTER_EN_SAUVEGARDANT;
        if (result.get() == quitteSeul)    return ResultatQuitter.QUITTER_SANS_SAUVEGARDER;
        return ResultatQuitter.NE_PAS_QUITTER;
    }


}