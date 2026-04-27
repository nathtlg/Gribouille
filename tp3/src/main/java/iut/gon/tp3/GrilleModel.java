package iut.gon.tp3;

public class GrilleModel {

    private String[][] tabCases = new String[3][3];

    public GrilleModel() {

        for (int lg = 0; lg < 3; lg++) {
            for (int col = 0; col < 3; col++) {
                tabCases[lg][col] = "L" + lg + "C" + col;
            }
        }
    }

    public String getCase(int lg, int col) {
        return tabCases[lg][col];
    }

    public void setCase(int lg, int col, String texte) {
        tabCases[lg][col] = texte;
    }
}
