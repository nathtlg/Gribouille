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
        dessin.widthProperty().addListener((o, ov, nv) -> controleur.dessine());
        dessin.heightProperty().addListener((o, ov, nv) -> controleur.dessine());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}; 
    
    public void efface() {
        dessin.getGraphicsContext2D().clearRect(0, 0, dessin.getWidth(), dessin.getHeight());
    }

    public void trace(double x1, double y1, double x2, double y2) {
        dessin.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
    }
    
    @FXML
    public void onMousePressed(MouseEvent evt) {
        controleur.onMousePress(evt.getX(), evt.getY());
    }
    
    @FXML
    public void onMouseDragged(MouseEvent evt) {
        controleur.onMouseDrag(evt.getX(), evt.getY());
    }
    
    @FXML
    public void onMouseMoved(MouseEvent evt) {
        controleur.precX.set(evt.getX());
        controleur.precY.set(evt.getY());
    }
    
    public void setCouleur(Color c) {
        dessin.getGraphicsContext2D().setStroke(c);
    }

    public void setEpaisseur(int ep) {
        dessin.getGraphicsContext2D().setLineWidth(ep);
    }

    
}