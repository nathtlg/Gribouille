package iut.gon.gribouille.controleurs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

public class CouleursController implements Initializable {
	@FXML private ColorPicker colorPicker;
    private Controleur controleur;
    private Rectangle rectPrecedent = null;

    public void setControleur(Controleur c) {
        this.controleur = c;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colorPicker.setOnAction(evt -> {
            controleur.setCouleur(colorPicker.getValue());
        });
    }
    
    @FXML
    public void onClicCouleur(MouseEvent evt) {
        if (evt.getTarget() instanceof Rectangle rect) {
            if (rectPrecedent != null) {
                rectPrecedent.setArcWidth(5);
                rectPrecedent.setArcHeight(5);
                rectPrecedent.setStrokeWidth(1);
            }
            rect.setArcWidth(10);
            rect.setArcHeight(10);
            rect.setStrokeWidth(5);
            rectPrecedent = rect;
            controleur.setCouleur((Color) rect.getFill());
        }
    }
}