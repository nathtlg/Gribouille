package iut.gon.tp3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GrilleController implements Initializable {

    @FXML
    private GridPane grille;

    private Label[][] labels = new Label[3][3];

    private GrilleModel model;

    public GrilleController(GrilleModel model) {
        this.model = model;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        grille.setStyle("-fx-background-color: seashell;");

        for (int lg = 0; lg < 3; lg++) {
            for (int col = 0; col < 3; col++) {

                Label lab = new Label();

                lab.textProperty().bind(model.getCase(lg, col));

                lab.setMaxSize(1000, 1000);
                lab.setAlignment(Pos.CENTER);

                int ligne = lg;
                int colonne = col;

                lab.setOnMouseClicked(e -> {

                    model.setCase(ligne, colonne, "bonjour");
                });

                grille.add(lab, col, lg);
                labels[lg][col] = lab;
            }
        }
    }
}