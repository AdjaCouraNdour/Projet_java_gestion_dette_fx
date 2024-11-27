package com.ism.controllers;

import java.io.IOException;
import java.util.List;

import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Client;

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
public class ClientListController {

    @FXML private TableView<Client>tabview ;
    @FXML private TableColumn<Client, String> nom;
    @FXML private TableColumn<Client, String> prenom;
    @FXML private TableColumn<Client, String> adresse;
    @FXML private TableColumn<Client, String> telephone;
    // @FXML private TableColumn<Client, Integer> id;
    private FactoryService factoryService ;


    public void initialize(){
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("address"));
        // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        factoryService= new FactoryService();
       loadTable();

    }

    public void loadTable(){
        List<Client> clients = factoryService.getInstanceClientService().show();
        System.out.println(clients);
        ObservableList<Client>clientList=FXCollections.observableArrayList(clients);
        tabview.setItems(clientList);
    };

      @FXML
        public void addClient() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/clientForm.fxml"));
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