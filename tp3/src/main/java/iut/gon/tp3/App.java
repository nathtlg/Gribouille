package iut.gon.tp3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        GrilleModel modele = new GrilleModel();
        GrilleController controller = new GrilleController(modele);

        FXMLLoader loader =
            new FXMLLoader(getClass().getResource("primary.fxml"));

        loader.setController(controller);

        Parent root = loader.load();

        scene = new Scene(root, 640, 480);

        stage.setScene(scene);
        scene.setOnKeyPressed(event -> {
            switch (event.getText()) {
                case "1": modele.setCase(2,0,"1"); break;
                case "2": modele.setCase(2,1,"2"); break;
                case "3": modele.setCase(2,2,"3"); break;
                case "4": modele.setCase(1,0,"4"); break;
                case "5": modele.setCase(1,1,"5"); break;
                case "6": modele.setCase(1,2,"6"); break;
                case "7": modele.setCase(0,0,"7"); break;
                case "8": modele.setCase(0,1,"8"); break;
                case "9": modele.setCase(0,2,"9"); break;
            }
        	});
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        fxmlLoader.setController(new GrilleController(new GrilleModel()));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}