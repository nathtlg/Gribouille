package fr.iutgon.tp6;

import fr.iutgon.tp6.modele.FabriqueProduits;
import fr.iutgon.tp6.modele.Ligne;
import fr.iutgon.tp6.modele.Produit;

import javafx.beans.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.*;

import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class FactureController implements Initializable {
  public TableView<Ligne> table;
  public TableColumn<Ligne, Integer> qte;
  public TableColumn<Ligne, Produit> produit;
  public TableColumn<Ligne, Number> prixUnitaire;
  public TableColumn<Ligne, Number> totalHT;
  public TableColumn<Ligne, Number> totalTTC;
  public TextField sommeFacture;

  /**
   Called to initialize a controller after its root element has been completely processed.

   @param location  The location used to resolve relative paths for the root object, or
   {@code null} if the location is not known.
   @param resources The resources used to localize the root object, or {@code null} if
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
	  qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
	  
	  produit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ligne, Produit>, ObservableValue<Produit>>(){

		@Override
		public ObservableValue<Produit> call(CellDataFeatures<Ligne, Produit> param) {
			return param.getValue().produitProperty();
		}
		  
	  }); 
	  
	  prixUnitaire.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ligne, Number>, ObservableValue<Number>>() {
		    @Override
		    public ObservableValue<Number> call(CellDataFeatures<Ligne, Number> param) {
		        return (ObservableValue<Number>) (ObservableValue<?>) param.getValue().produitProperty().get().prixProperty().asObject();
		    }
		});
	  

		totalHT.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ligne, Number>, ObservableValue<Number>>() {
			
		    @Override
		    public ObservableValue<Number> call(CellDataFeatures<Ligne, Number> param) {
		        return param.getValue().totalHTProperty();
		    }
		});
		
		totalTTC.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ligne, Number>, ObservableValue<Number>>() {
		    @Override
		    public ObservableValue<Number> call(CellDataFeatures<Ligne, Number> param) {
		        return param.getValue().totalTTCProperty();
		    }
		});
	  
	  
  }

  public void onAjouter(ActionEvent actionEvent) {
	  List<Produit> produits = FabriqueProduits.getProduits();
	  Random random = new Random(); 
	  Produit produitAleatoire = produits.get(random.nextInt(produits.size()));
	  int qteAleatoire = random.nextInt(10) + 1;
	  Ligne ligne = new Ligne(qteAleatoire, produitAleatoire);
	  table.getItems().add(ligne);
	  
	  rebindSomme(); 
  }
  
  private void rebindSomme() {
	    ObservableList<Ligne> items = table.getItems();
	    if (items.isEmpty()) {
	      sommeFacture.textProperty().unbind();
	      sommeFacture.setText("0.00 €");
	      return;
	    }
  } 
  
}
