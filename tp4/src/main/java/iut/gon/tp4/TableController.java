package iut.gon.tp4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    public void onFermer(ActionEvent event) {
        // TODO fermer la fenêtre
    }

    public void onReinit(ActionEvent event) {
        scores.reinitialiser();
    }
}