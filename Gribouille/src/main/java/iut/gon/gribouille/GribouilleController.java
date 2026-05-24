package iut.gon.gribouille;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import iut.gon.gribouille.modele.Dessin;
import iut.gon.gribouille.modele.Figure;
import iut.gon.gribouille.modele.Point;
import iut.gon.gribouille.modele.Trace;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GribouilleController implements Initializable{
	@FXML private Pane pane; 
	@FXML private Canvas dessin; 
	@FXML private Label labelX; 
	@FXML private Label labelY;
	@FXML private Label labelEpaisseur; 
	@FXML private Label labelOutil; 
	@FXML private Label labelCouleur; 
	@FXML private ColorPicker colorPicker; 
	
	private SimpleDoubleProperty prevX = new SimpleDoubleProperty(0);
	private SimpleDoubleProperty prevY = new SimpleDoubleProperty(0);  
	private Trace traceCourant; 
	private Dessin dessinModele; 	
	
	public GribouilleController(Dessin dessinModele) {
		this.dessinModele = dessinModele; 
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		dessin.widthProperty().bind(pane.widthProperty()); 
		dessin.widthProperty().addListener((obs, oldVal, NewVal) -> redessine());
		dessin.heightProperty().bind(pane.heightProperty()); 
		dessin.heightProperty().addListener((obs, oldVal, NewVal) -> redessine());
		labelX.textProperty().bind(prevX.asString("%.0f"));
		labelY.textProperty().bind(prevY.asString("%.0f"));
	}
	
	@FXML
	public void onMousePressed(MouseEvent evt) {
		prevX.set(evt.getX()); 
		prevY.set(evt.getY());  
		traceCourant = new Trace(1, "BLACK", prevX.get(), prevY.get()); 
		dessinModele.addFigure(traceCourant);
	}
	
	@FXML 
	public void onMouseDragged(MouseEvent evt) {
		double x = evt.getX(), y = evt.getY(); 
		dessin.getGraphicsContext2D().strokeLine(prevX.get(), prevY.get(), evt.getX(), evt.getY()); 
		traceCourant.addPoint(x, y);
		prevX.set(x);
		prevY.set(y);
	}
	
	@FXML
	public void onMouseMoved(MouseEvent evt) {
		prevX.set(evt.getX()); 
		prevY.set(evt.getY()); 
	}
	
	private void redessine() {
		GraphicsContext gc = dessin.getGraphicsContext2D(); 
		gc.clearRect(0, 0, dessin.getWidth(), dessin.getHeight());
		
		for (Figure f : dessinModele.getFigures()) {
			gc.setStroke(Color.valueOf(f.getCouleur()));
			gc.setLineWidth(f.getEpaisseur());
			List<Point> points = f.getPoints(); 
			
			for (int i = 0; i < points.size() - 1; i++) {
				Point a = points.get(i); 
				Point b = points.get(i + 1); 
				gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
			}
		}
		
	}
}
