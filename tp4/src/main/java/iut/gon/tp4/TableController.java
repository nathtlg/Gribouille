package iut.gon.tp4;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController {

    @FXML
    public Label nbNulles;

    @FXML
    public TableView<Scores.Entree> table;

    private Scores scores;

    public TableController() {
    }

    public void setScores(Scores scores) {
        this.scores = scores;

        table.setItems(scores.getScores());

        table.getColumns().get(0)
                .setCellValueFactory(new PropertyValueFactory<>("joueur"));

        table.getColumns().get(1)
                .setCellValueFactory(new PropertyValueFactory<>("victoires"));

        nbNulles.setText(String.valueOf(scores.getNulles()));
    }

    @FXML
    public void onFermer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("grille.fxml"));

            // IMPORTANT : recréer ou récupérer ton controller avec scores
            GrilleController controller = new GrilleController(scores);
            loader.setController(controller);

            Parent root = loader.load();

            table.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onReinit(ActionEvent event) {
        scores.reinitialiser();
    }
}