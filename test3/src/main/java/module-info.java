module iut.gon.test3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens iut.gon.test3 to javafx.fxml;
    exports iut.gon.test3;
}
