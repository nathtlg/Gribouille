package fr.unicaen.iut.tp5;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleurDemineur implements Initializable {

    private ModeleDemineur modele;

    @FXML
    private ToggleGroup niveau;

    @FXML
    private TextField NbInconnues;

    @FXML
    private TextField NbMarques;

    @FXML
    private GridPane grille;

    private final Background inconnu = new Background(
            new BackgroundFill(Color.AQUA, new CornerRadii(20), null)
    );

    private final Background libre = new Background(
            new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, null)
    );

    private final Background echec = new Background(
            new BackgroundFill(Color.RED, CornerRadii.EMPTY, null)
    );

    private final Background marquee = new Background(
            new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, null)
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initGrille("10;10;10");

        niveau.selectedToggleProperty().addListener((obs, oldT, newT) -> {
            if (newT != null) {
                initGrille(newT.getUserData().toString());
            }
        });
    }

    private void initGrille(String userData) {

        grille.getChildren().clear();
        grille.getColumnConstraints().clear();
        grille.getRowConstraints().clear();

        int[] vals = ModeleDemineur.parseUserData(userData);

        int nbColonnes = vals[0];
        int nbLignes = vals[1];
        int nbMines = vals[2];

        modele = new ModeleDemineur(nbLignes, nbColonnes, nbMines);

        NbInconnues.textProperty().unbind();
        NbMarques.textProperty().unbind();

        NbInconnues.textProperty().bind(modele.nbInconnuesProperty().asString());
        NbMarques.textProperty().bind(modele.nbMarquesProperty().asString());

        for (int i = 0; i < nbColonnes; i++) {
            grille.getColumnConstraints().add(new ColumnConstraints(32));
        }

        for (int i = 0; i < nbLignes; i++) {
            grille.getRowConstraints().add(new RowConstraints(32));
        }

        for (int y = 0; y < nbLignes; y++) {
            for (int x = 0; x < nbColonnes; x++) {

                Label lbl = new Label();
                lbl.setPrefSize(32, 32);
                lbl.setMinSize(32, 32);
                lbl.setMaxSize(32, 32);
                lbl.setAlignment(Pos.CENTER);
                lbl.setBackground(inconnu);

                int c = x;
                int l = y;

                lbl.textProperty().bind(
                        modele.texteProperty(l, c)
                );

                lbl.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

                    if (modele.estPerdu()) return;

                    if (event.getButton() == MouseButton.PRIMARY) {
                        modele.revele(l, c);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        modele.marque(l, c);
                    }

                    if (modele.estMarquee(l, c)) {
                        lbl.setBackground(marquee);
                    } else if (!modele.estRevelee(l, c)) {
                        lbl.setBackground(inconnu);
                    } else if (modele.getText(l, c).equals("X")) {
                        lbl.setBackground(echec);
                    } else {
                        lbl.setBackground(libre);
                    }
                });

                grille.add(lbl, x, y);
            }
        }

        grille.setPrefSize(nbColonnes * 32, nbLignes * 32);

        if (grille.getScene() != null && grille.getScene().getWindow() != null) {
            grille.getScene().getWindow().sizeToScene();
        }
    }
}