package iut.gon.tp4;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GrilleController implements Initializable {

  private GrilleModel modele;
  private Scores table;

  public GrilleController(Scores table) {
	    this.modele = new GrilleModel();
	    this.table = table;
	}

  private @FXML GridPane grille;
  private @FXML HBox statut;
  private @FXML Label joueur;

  private Label[][] contenu = new Label[3][3];


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    grille.setStyle("-fx-background-color: seashell");
    for (int l=0; l<3; ++l)
      for (int c=0; c<3; ++c) {
        Label label =new Label();
        label.textProperty().bind(modele.getCase(l,c));
        grille.add(label, c, l);
        int lg = l;
        int col = c;
        label.setOnMouseClicked(event -> this.joueCase(lg, col));
        label.setMaxSize(1000,1000);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(24));
      }
    joueur.textProperty().bind(modele.texteJoueur);
  }

  public void joueCase(int lg, int col) {
    if (modele.estFinie()) return;
    try {
      modele.joueCase(lg, col);
    } catch (IllegalStateException ex) {
      new Alert(Alert.AlertType.ERROR,ex.getMessage()).showAndWait();
      return;
    }
    if (modele.estGagne(modele.JOUEUR_X))
      onGagne(modele.JOUEUR_X);
    else if (modele.estGagne(modele.JOUEUR_O))
      onGagne(modele.JOUEUR_O);
    else if (modele.estFinie())
      onGagne(null);
  }

  private void onGagne(String joueurGagnant) {

	    if (joueurGagnant != null) {
	        TextInputDialog dialog = new TextInputDialog();
	        dialog.setTitle("Victoire !");
	        dialog.setHeaderText("Joueur " + joueurGagnant + " a gagné !");
	        dialog.setContentText("Nom du joueur :");

	        Optional<String> result = dialog.showAndWait();

	        result.ifPresent(nom -> {
	            table.ajouteVictoire(nom);
	        });

	    } else {
	        table.ajouteNulle();
	    }

	    afficherTable();
	}
  
  private void afficherTable() {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("table.fxml"));
	        Parent root = loader.load();

	        TableController controller = loader.getController();
	        controller.setScores(table);

	        grille.getScene().setRoot(root);

	    } catch (IOException e) {
	        new Alert(Alert.AlertType.ERROR, "Erreur chargement table").showAndWait();
	    }
	}

  @FXML
  public void onMenuNouvelle(ActionEvent evt) {
	    modele.nouvellePartie();
	  }
  @FXML
  public void onMenuTable(ActionEvent event) {
      try {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("table.fxml"));
          Parent root = loader.load();

    
          TableController controller = loader.getController();
          controller.setScores(table);

          grille.getScene().setRoot(root);

      } catch (IOException e) {
          new Alert(Alert.AlertType.ERROR, "Impossible de charger la table des scores").showAndWait();
      }
  }

  @FXML
  public void onMenuQuitter(ActionEvent evt) {
    Platform.exit();
  }
}
