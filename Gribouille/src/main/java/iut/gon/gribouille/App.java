package iut.gon.gribouille;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import iut.gon.gribouille.controleurs.Controleur;
import iut.gon.gribouille.modele.Dessin;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	Dessin dessin = new Dessin(); 
    	FXMLLoader loader = new FXMLLoader(App.class.getResource("CadreGribouille.fxml")); 
    	Parent root = loader.load(); 
    	Controleur controleur = loader.getController();
    	
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.titleProperty().bind(controleur.dessin.nomDuFichierProperty());

        stage.setOnCloseRequest(event -> {
            if (!controleur.onQuitter()) {
                event.consume();
            }
        });
        
        

        stage.show();
    }
    
    
    

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}