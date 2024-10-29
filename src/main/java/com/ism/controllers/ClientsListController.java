package com.ism.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Client;

import java.util.List;

public class ClientsListController {

    @FXML private TableView<Client> clientsTable;
    @FXML private TableColumn<Client, String> nomColumn;
    @FXML private TableColumn<Client, String> prenomColumn;
    @FXML private TableColumn<Client, String> telephoneColumn;
    @FXML private TableColumn<Client, String> adresseColumn;

    private ObservableList<Client> clientList = FXCollections.observableArrayList();
    private FactoryService factoryService = new FactoryService();

    @FXML
    private void initialize() {
        // Configuration des colonnes
        nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        prenomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        telephoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelephone()));
        adresseColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        
        VBox vbox = new VBox(); // Créez un VBox
        VBox.setMargin(clientsTable, new Insets(10, 0, 0, 50)); // Marge : haut, droite, bas, gauche
        vbox.getChildren().add(clientsTable); // Ajoutez le tableau au VBox

        // Initialiser la liste des clients
        listClients();
    }
    @FXML
    private void listClients() {
        clientList.clear(); // Effacez les données précédentes
    
        try {
            // Récupérer tous les clients depuis le service
            List<Client> clients = factoryService.getInstanceClientService().show();
            clientList.addAll(clients); // Remplir l'ObservableList
            clientsTable.setItems(clientList); // Mettre à jour le tableau
        } catch (Exception e) {
            // Gérer l'erreur, par exemple en affichant une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors du chargement des clients : " + e.getMessage());
            alert.showAndWait();
        }
    }
    

}
