package iut.gon.gribouille;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("CadreGribouille"), 640, 480);
        stage.setScene(scene);
        
        Canvas dessin = (Canvas) scene.lookup("Canvas"); 
        
        double[] prev = {0, 0}; 
        
        dessin.addEventHandler(MouseEvent.MOUSE_PRESSED, evt -> {
        	prev[0] = evt.getX(); 
        	prev[1] = evt.getX(); 
        }); 
        
        dessin.addEventHandler(MouseEvent.MOUSE_DRAGGED, evt -> {
        	dessin.getGraphicsContext2D().strokeLine(prev[0], prev[1], evt.getX(), evt.getY()); 
        	prev[0] = evt.getX(); 
        	prev[1] = evt.getY(); 
        	
        });

        stage.setOnCloseRequest(event -> {
            if (!Dialogues.confirmation()) {
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