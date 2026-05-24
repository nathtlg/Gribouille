package iut.gon.gribouille.controleurs;

import iut.gon.gribouille.Dialogues;
import iut.gon.gribouille.modele.Dessin;
import iut.gon.gribouille.modele.Figure;
import iut.gon.gribouille.modele.Point;
import iut.gon.gribouille.modele.Trace;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML private MenusController menusController;
    @FXML private DessinController dessinController;
    @FXML private CouleursController couleursController;
    @FXML private StatutController statutController;

    public final Dessin dessin = new Dessin();

    public final SimpleObjectProperty<Color> couleur = new SimpleObjectProperty<>(Color.BLACK);
    public final SimpleIntegerProperty epaisseur = new SimpleIntegerProperty(1);
    public final SimpleDoubleProperty precX = new SimpleDoubleProperty(0);
    public final SimpleDoubleProperty precY = new SimpleDoubleProperty(0);

    public Figure figureCourante;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	menusController.setControleur(this);
        dessinController.setControleur(this);
        couleursController.setControleur(this);
        statutController.setControleur(this);
        
        statutController.labelX.textProperty().bind(precX.asString("%.0f"));
        statutController.labelY.textProperty().bind(precY.asString("%.0f")); 
        
        statutController.labelEpaisseur.textProperty().bind(epaisseur.asString());
        }

    public boolean onQuitter() {
        return Dialogues.confirmation();
    }

    public void dessine() {
        dessinController.efface();
        for (Figure f : dessin.getFigures()) {
            dessinController.setCouleur(Color.valueOf(f.getCouleur()));
            dessinController.setEpaisseur(f.getEpaisseur());
            List<Point> points = f.getPoints();
            for (int i = 0; i < points.size() - 1; i++) {
                Point a = points.get(i);
                Point b = points.get(i + 1);
                dessinController.trace(a.getX(), a.getY(), b.getX(), b.getY());
            }
        }
    }

	public void onMousePress(double x, double y) {
	    precX.set(x);
	    precY.set(y);
	    figureCourante = new Trace(epaisseur.get(), couleur.get().toString(), x, y);
	    dessin.addFigure(figureCourante);
	}

	public void onMouseDrag(double x, double y) {
	    dessinController.trace(precX.get(), precY.get(), x, y);
	    figureCourante.addPoint(x, y);
	    precX.set(x);
	    precY.set(y);
	}
}