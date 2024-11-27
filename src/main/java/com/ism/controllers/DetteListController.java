package com.ism.controllers;

import java.io.IOException;
import java.util.List;

import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Dette;
import com.ism.data.enums.TypeDette;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
public class DetteListController {

    @FXML private TableView<Dette>tabview ;
    @FXML private TableColumn<Dette, String> montant;
    @FXML private TableColumn<Dette, String> montantRestant;
    @FXML private TableColumn<Dette, Integer> id;
    @FXML private TableColumn<Dette, TypeDette> typeDette;
    private FactoryService factoryService ;


    public void initialize(){
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        montantRestant.setCellValueFactory(new PropertyValueFactory<>("montantRestant"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeDette.setCellValueFactory(new PropertyValueFactory<>("typeDette"));

        factoryService= new FactoryService();
       loadTable();

    }

    public void loadTable(){
        List<Dette> Dettes = factoryService.getInstanceDetteService().show();
        System.out.println(Dettes);
        ObservableList<Dette>DetteList=FXCollections.observableArrayList(Dettes);
        tabview.setItems(DetteList);
    };

    @FXML
    public void listUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/listerUsers.fxml"));
            Parent root = loader.load();
            
            // Remplacez la scène ou la fenêtre actuelle avec la nouvelle vue
            Stage stage = (Stage) tabview.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Gérez l'exception de manière appropriée
        }
    }
      @FXML
        private void addDette() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/detteForm.fxml"));
                Parent root = loader.load();
                
                // Remplacez la scène ou la fenêtre actuelle avec la nouvelle vue
                Stage stage = (Stage) tabview.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Gérez l'exception de manière appropriée
            }
        }
        @FXML
        public void listClient() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/listerClients.fxml"));
                Parent root = loader.load();
                
                // Remplacez la scène ou la fenêtre actuelle avec la nouvelle vue
                Stage stage = (Stage) tabview.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Gérez l'exception de manière appropriée
            }
        }
        @FXML
        public void listArticle() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/listerArticles.fxml"));
                Parent root = loader.load();
                
                // Remplacez la scène ou la fenêtre actuelle avec la nouvelle vue
                Stage stage = (Stage) tabview.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Gérez l'exception de manière appropriée
            }
        }
        @FXML
        public void listDette() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/listerDettes.fxml"));
                Parent root = loader.load();
                
                // Remplacez la scène ou la fenêtre actuelle avec la nouvelle vue
                Stage stage = (Stage) tabview.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Gérez l'exception de manière appropriée
            }
        }
        @FXML
        public void listPaiement() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/listerPaiements.fxml"));
                Parent root = loader.load();
                
                // Remplacez la scène ou la fenêtre actuelle avec la nouvelle vue
                Stage stage = (Stage) tabview.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Gérez l'exception de manière appropriée
            }
        }
}






