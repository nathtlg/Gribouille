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
import iut.gon.gribouille.*;

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

    private Outil outilCourant = new OutilCrayon(this);

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
        outilCourant.onMousePress(x, y);
    }

    public void onMouseDrag(double x, double y) {
        outilCourant.onMouseDrag(x, y);
        precX.set(x);
        precY.set(y);
    }
	
	public void traceLigne(double x1, double y1, double x2, double y2) {
	    dessinController.trace(x1, y1, x2, y2);
	}
	
	public void onCrayon() {
	    outilCourant = new OutilCrayon(this);
	    statutController.labelOutil.setText("Crayon");
	}

	public void onEtoile() {
	    outilCourant = new OutilEtoile(this);
	    statutController.labelOutil.setText("Étoile");
	}
	
	public void setCouleur(Color c) {
	    couleur.set(c);
	    dessinController.setCouleur(c);
	}
	
	public void setEpaisseur(int ep) {
	    epaisseur.set(ep);
	    dessinController.setEpaisseur(ep);
	}
}