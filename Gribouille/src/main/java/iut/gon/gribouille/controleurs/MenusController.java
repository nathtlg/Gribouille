package iut.gon.gribouille.controleurs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import java.net.URL;
import java.util.ResourceBundle;

public class MenusController implements Initializable {
    @FXML public ToggleGroup groupeEpaisseur;
    @FXML public ToggleGroup groupeOutil;
    @FXML public RadioMenuItem menuCrayon;
    @FXML public RadioMenuItem menuEtoile;

    private Controleur controleur;

    public void setControleur(Controleur c) {
        this.controleur = c;
        groupeOutil.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
        	if (newVal == menuCrayon) controleur.onCrayon(); 
        	else if (newVal == menuEtoile) controleur.onEtoile(); 
        });
        
        groupeEpaisseur.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                String texte = ((RadioMenuItem) newVal).getText();
                controleur.setEpaisseur(Integer.parseInt(texte));
            }
        });
    }
    
    @FXML
    public void onCharger() {
        controleur.charge();
    }
    
    @FXML
    public void onEffacer() {
        controleur.effacer();
    }

    @FXML
    public void onSauvegarder() {
        controleur.sauvegarde();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML
    public void onQuitte() {
        if (controleur.onQuitter()) {
            Platform.exit();
        }
    }
}