package com.ism.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Dette;
import com.ism.data.entities.Paiement;

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

public class PaiementListController {

    @FXML private TableView<Paiement>tabview ;
    @FXML private TableColumn<Paiement, Dette> dette;
    @FXML private TableColumn<Paiement, Date> date;
    @FXML private TableColumn<Paiement, String> montant;
    @FXML private TableColumn<Paiement, Integer> id;
    private FactoryService factoryService ;


    public void initialize(){
        dette.setCellValueFactory(new PropertyValueFactory<>("dette"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        factoryService= new FactoryService();
        loadTable();

    }

    public void loadTable(){
        List<Paiement> Paiements = factoryService.getInstancePaiementService().show();
        System.out.println(Paiements);
        ObservableList<Paiement>PaiementList=FXCollections.observableArrayList(Paiements);
        tabview.setItems(PaiementList);
    };
        @FXML
        public void addPaiement() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/paiementForm.fxml"));
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






