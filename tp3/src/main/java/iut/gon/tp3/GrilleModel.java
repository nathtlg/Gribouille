package iut.gon.tp3;

import javafx.beans.property.SimpleStringProperty;

public class GrilleModel {

    private SimpleStringProperty[][] tabCases = new SimpleStringProperty[3][3];

    public GrilleModel() {

        for (int lg = 0; lg < 3; lg++) {
            for (int col = 0; col < 3; col++) {
                tabCases[lg][col] = new SimpleStringProperty("L" + lg + "C" + col);
            }
        }
    }

    public SimpleStringProperty getCase(int lg, int col) {
        return tabCases[lg][col];
    }

    public void setCase(int lg, int col, String texte) {
        tabCases[lg][col].setValue(texte);
    }
}
