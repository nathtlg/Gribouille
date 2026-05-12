package fr.unicaen.iut.tp5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainDemineur extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource("view.fxml")
                );

        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.show();
        stage.sizeToScene();  
        stage.setResizable(true); 
    }

    public static void main(String[] args) {
        launch(args);
    }
}
