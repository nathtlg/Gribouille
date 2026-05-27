package iut.gon.gribouille;

import iut.gon.gribouille.controleurs.Controleur;
import iut.gon.gribouille.modele.Point;
import iut.gon.gribouille.modele.Trace;

public class OutilCrayon extends Outil{
	private Trace traceCourante;

	public OutilCrayon(Controleur c) {
		super(c);
	}

	@Override
	public void onMousePress(double x, double y) {
		traceCourante = new Trace(controleur.epaisseur.get(), controleur.couleur.get().toString(), x, y);
		controleur.dessin.addFigure(traceCourante);
	}

	@Override
	public void onMouseDrag(double x, double y) {
	    controleur.traceLigne(controleur.precX.get(), controleur.precY.get(), x, y);
	    traceCourante.addPoint(x, y);
	}
}
	
