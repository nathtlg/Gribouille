package iut.gon.gribouille.controleurs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class StatutController implements Initializable {
    @FXML public Label labelX;
    @FXML public Label labelY;
    @FXML public Label labelEpaisseur;
    @FXML public Label labelOutil;
    @FXML public Label labelCouleur;

    private Controleur controleur;

    public void setControleur(Controleur c) {
        this.controleur = c;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}