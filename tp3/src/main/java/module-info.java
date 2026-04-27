module iut.gon.tp3 {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens iut.gon.tp3 to javafx.fxml;
    exports iut.gon.tp3;
}
