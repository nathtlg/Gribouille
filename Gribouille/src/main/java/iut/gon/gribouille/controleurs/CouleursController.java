package iut.gon.gribouille.controleurs;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class CouleursController implements Initializable {
    private Controleur controleur;

    public void setControleur(Controleur c) {
        this.controleur = c;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
}