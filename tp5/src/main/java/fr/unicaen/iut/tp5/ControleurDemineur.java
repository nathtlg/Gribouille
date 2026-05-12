package fr.unicaen.iut.tp5;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleurDemineur implements Initializable {

    @FXML
    private ToggleGroup niveau;

    @FXML
    private TextField NbInconnues;

    @FXML
    private TextField NbMarques;

    @FXML
    private GridPane grille;

    private ModeleDemineur modele;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        modele = new ModeleDemineur(10, 10, 10);

        NbInconnues.textProperty().bind(
                modele.nbInconnuesProperty().asString()
        );

        NbMarques.textProperty().bind(
                modele.nbMarquesProperty().asString()
        ); 
        
        niveau.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                initGrille(newToggle.getUserData().toString());
            }
        });
        
    }
    
    private void initGrille(String userData) {

        int[] valeurs =
                ModeleDemineur.parseUserData(userData);

        int tailleY = valeurs[0];
        int tailleX = valeurs[1];
        int nbMines = valeurs[2];

        modele.setTaille(tailleY, tailleX, nbMines);

        grille.getChildren().clear();

        System.out.println(
                "Nouvelle grille : "
                        + tailleY + " x "
                        + tailleX
                        + " avec "
                        + nbMines + " mines."
        );
    }
}
