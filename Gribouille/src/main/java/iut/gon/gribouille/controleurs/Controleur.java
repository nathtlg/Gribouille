package iut.gon.gribouille.controleurs;

import iut.gon.gribouille.Dialogues;
import iut.gon.gribouille.modele.Dessin;
import iut.gon.gribouille.modele.Figure;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML private MenusController menusController;
    @FXML private DessinController dessinController;
    @FXML private CouleursController couleursController;
    @FXML private StatutController statutController;

    public final Dessin dessin = new Dessin();

    public final SimpleObjectProperty<Color> couleur = new SimpleObjectProperty<>(Color.BLACK);
    public final SimpleIntegerProperty epaisseur = new SimpleIntegerProperty(1);
    public double precX, precY;

    public Figure figureCourante;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	menusController.setControleur(this);
        dessinController.setControleur(this);
        couleursController.setControleur(this);
        statutController.setControleur(this);

        statutController.labelEpaisseur.textProperty().bind(epaisseur.asString());
    }

    public boolean onQuitter() {
        return Dialogues.confirmation();
    }

	public Object redessine() {
		// TODO Auto-generated method stub
		return null;
	}
}