package iut.gon.tp4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenusController {

    private GrilleModel modele;
    private Scores scores;
    private GrilleController grilleController;

    public void setParams(GrilleModel modele, Scores scores, GrilleController grilleController) {
        this.modele = modele;
        this.scores = scores;
        this.grilleController = grilleController;
    }

    @FXML
    public void onMenuNouvelle(ActionEvent evt) {
        modele.nouvellePartie();
    }

    @FXML
    public void onMenuTable(ActionEvent event) {
        grilleController.afficherTable();
    }

    @FXML
    public void onMenuQuitter(ActionEvent evt) {
        javafx.application.Platform.exit();
    }
}