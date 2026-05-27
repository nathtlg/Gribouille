package iut.gon.gribouille;

import iut.gon.gribouille.controleurs.Controleur;
import iut.gon.gribouille.modele.Etoile;
import iut.gon.gribouille.modele.Point;

public class OutilEtoile extends Outil{
	private Etoile etoileCourante;

	public OutilEtoile(Controleur c) {
		super(c);
	}

	@Override
	public void onMousePress(double x, double y) {
		etoileCourante = new Etoile(controleur.epaisseur.get(), controleur.couleur.get().toString(), x, y);
		controleur.dessin.addFigure(etoileCourante);
	}

	@Override
	public void onMouseDrag(double x, double y) {
		Point centre = etoileCourante.getCentre(); 
		controleur.traceLigne(centre.getX(), centre.getY(), x, y); 
		etoileCourante.addPoint(x, y);
	} 
	
}
