package iut.gon.gribouille;

import iut.gon.gribouille.controleurs.Controleur;

public abstract class Outil {
    protected Controleur controleur;

    public Outil(Controleur c) { this.controleur = c; }

    public abstract void onMousePress(double x, double y);
    public abstract void onMouseDrag(double x, double y);
}