package iut.gon.tp2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TP2App extends Application {

  private BorderPane contenu;
  private ListView<String> gauche;
  private ListView<String> droite;
  private Button versGauche;
  private Button versDroite;
  private Button retireTout;
  private Button ajouteTout;

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(TP2App.class.getResource("Tp2.fxml"));
    contenu = fxmlLoader.load();
    Scene scene = new Scene(contenu);
    extraitIds(scene);

    prepareMenus((MenuBar) scene.lookup("#menus"));
    prepareListe();
    prepareBoutons();
    prepareFermeture(stage);

    stage.setTitle("Gestion de groupe");
    stage.setScene(scene);
    stage.show();
  }

  /** Prépare la fenêtre pour demander confirmation avant fermeture */
  private void prepareFermeture(Stage stage) {
    stage.setOnCloseRequest(event -> {
      //TODO confirmer ou consommer l'événement
    });
  }

  /** Prépare les actions des boutons */
  private void prepareBoutons() {
	    ajouteTout.setOnAction(this::onAjouteTout);
	    retireTout.setOnAction(this::onRetireTout);

	    versDroite.setOnAction(this::onVersDroite);
	    versGauche.setOnAction(this::onVersGauche);
    // TODO actions des deux boutons centraux
  }
  
  private void onVersDroite(ActionEvent actionEvent) {
	    String selection = gauche.getSelectionModel().getSelectedItem();

	    if (selection != null) {
	        gauche.getItems().remove(selection);
	        droite.getItems().add(selection);
	    }
	}
  
  private void onVersGauche(ActionEvent actionEvent) {
	    String selection = droite.getSelectionModel().getSelectedItem();

	    if (selection != null) {
	        droite.getItems().remove(selection);
	        gauche.getItems().add(selection);
	    }
	}

  /** Ajoute tous les éléments de gauche dans la liste de droite
   Active le bouton "Retirer tout" et désactive le bouton "Ajouter tout" */
  private void onAjouteTout(ActionEvent actionEvent) {
	    droite.getItems().addAll(gauche.getItems());
	    gauche.getItems().clear();

	    ajouteTout.setDisable(true);
	    retireTout.setDisable(false);
	}

  /** Ajoute tous les éléments de droite dans la liste de gauche
   Active le bouton "Ajouter tout" et désactive le bouton "Retirer tout" */
  private void onRetireTout(ActionEvent actionEvent) {
	    gauche.getItems().addAll(droite.getItems());
	    droite.getItems().clear();

	    ajouteTout.setDisable(false);
	    retireTout.setDisable(true);
	}

  /** Prépare les menus et leurs événements */
  private void prepareMenus(MenuBar menus) {

	    // Création des menus
	    Menu fichiers = new Menu("_Fichiers");
	    Menu aide = new Menu("_Aide");

	    // Création des items
	    MenuItem quitter = new MenuItem("Quitter");
	    MenuItem apropos = new MenuItem("À propos");

	    // Ajout des items dans les menus
	    fichiers.getItems().add(quitter);
	    aide.getItems().add(apropos);

	    // Ajout des menus dans la barre
	    menus.getMenus().addAll(fichiers, aide);

	    // Action Quitter
	    quitter.setOnAction(event -> Platform.exit());

	    // Action À propos
	    apropos.setOnAction(event -> {
	        Alert alert = new Alert(Alert.AlertType.NONE, "", ButtonType.CLOSE);
	        alert.setTitle("À propos");
	        alert.setContentText("Fait par vous !");
	        alert.show();
	    });
	}

  /**
   Remplit la liste de gauche avec des valeurs
   Active le bouton "Ajouter tout"
   */
  private void prepareListe() {
	    gauche.getItems().addAll(
	            "JFA",
	            "Momo",
	            "Leo",
	            "Nathan",
	            "Azelann"
	    );

	    ajouteTout.setDisable(false);
	}

  private void extraitIds(Scene scene) {
    gauche = (ListView<String>) scene.lookup("#gauche");
    droite = (ListView<String>) scene.lookup("#droite");
    versGauche = (Button) scene.lookup("#versGauche");
    versDroite = (Button) scene.lookup("#versDroite");
    retireTout = (Button) scene.lookup("#retireTout");
    ajouteTout = (Button) scene.lookup("#ajouteTout");
  }

  public static void main(String[] args) {
    launch();
  }
}
