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

    private Label[][] tabLabels = new Label[3][3];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        grille.setStyle("-fx-background-color: seashell;");

        for (int lg = 0; lg < 3; lg++) {
            for (int col = 0; col < 3; col++) {

                Label lab = new Label();

                lab.setText(String.format("L%dC%d", lg +1, col+1));

                lab.setMaxSize(1000, 1000);

                lab.setAlignment(Pos.CENTER);

                lab.setStyle("-fx-border-color: black; -fx-font-size: 18;");

                lab.setOnMouseClicked(e -> {
                    lab.setText("bonjour");
                });

                grille.add(lab, col, lg);

                tabLabels[lg][col] = lab;
            }
        }
    }
}


