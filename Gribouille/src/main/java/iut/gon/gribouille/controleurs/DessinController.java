package iut.gon.gribouille.controleurs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;

public class DessinController implements Initializable {
    @FXML public Canvas dessin;
    @FXML public Pane pane;

    private Controleur controleur;

    public void setControleur(Controleur c) {
        this.controleur = c;
        dessin.widthProperty().bind(pane.widthProperty());
        dessin.heightProperty().bind(pane.heightProperty());
        dessin.widthProperty().addListener((o, ov, nv) -> controleur.redessine());
        dessin.heightProperty().addListener((o, ov, nv) -> controleur.redessine());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}; 
    
}