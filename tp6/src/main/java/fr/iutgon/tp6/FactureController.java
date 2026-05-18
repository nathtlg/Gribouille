package fr.iutgon.tp6;

import fr.iutgon.tp6.modele.FabriqueProduits;
import fr.iutgon.tp6.modele.Ligne;
import fr.iutgon.tp6.modele.Produit;

import javafx.beans.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.*;
import javafx.util.converter.IntegerStringConverter;

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
		        return Bindings.select(param.getValue().produitProperty(), "prix");
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
		
	    qte.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	    qte.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ligne, Integer>>() {
	        @Override
	        public void handle(TableColumn.CellEditEvent<Ligne, Integer> event) {
	            event.getRowValue().setQte(event.getNewValue());
	        }
	    });
	    
	    List<Produit> listeProduits = FabriqueProduits.getProduits();
	    ObservableList<Produit> produitObservable = FXCollections.observableList(listeProduits);

	    StringConverter<Produit> produitConverter = new StringConverter<>() {
	        @Override
	        public String toString(Produit p) {
	          return p == null ? "" : p.getNom();
	        }
	        
	        @Override
	        public Produit fromString(String nom) {
	            for (Produit p : listeProduits) {
	                if (p.getNom().equals(nom)) {
	                    return p;
	                }
	            }
	            return null;
	        }
	      };
	      
	      
	      produit.setCellFactory(ChoiceBoxTableCell.forTableColumn(produitConverter, produitObservable));
	      produit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ligne, Produit>>() {
	    	    @Override
	    	    
	    	    public void handle(TableColumn.CellEditEvent<Ligne, Produit> event) {
	    	        event.getRowValue().setProduit(event.getNewValue());
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
	    
	    NumberExpression somme = items.get(0).totalTTCProperty();
	    for (int i = 1; i < items.size(); i++) {
	      somme = Bindings.add(somme, items.get(i).totalTTCProperty());
	    }
	    
	    NumberExpression sommeFinal = somme;
	    sommeFacture.textProperty().bind(
	        Bindings.createStringBinding(
	            () -> String.format("%.2f €", sommeFinal.floatValue()),
	            sommeFinal
	        )
	    );


  }
  
  
  
}
