package com.ism.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.ism.UserConnect;
import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Client;
import com.ism.data.entities.Dette;
import com.ism.data.entities.Paiement;
import com.ism.data.enums.UserRole;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;

public class PaiementListController {

    @FXML private TableView<Paiement>tabview ;
    @FXML private TableColumn<Paiement, Dette> dette;
    @FXML private TableColumn<Paiement, Date> date;
    @FXML private TableColumn<Paiement, String> montant;
    @FXML private TableColumn<Paiement, Integer> id;
    private FactoryService factoryService ;
    private UserConnect connectedUser;

    public void setConnectedUser(UserConnect connectedUser) {
        this.connectedUser = connectedUser;
    }

    public void initialize(){
        dette.setCellValueFactory(new PropertyValueFactory<>("dette"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        factoryService= new FactoryService();
        loadTable();

    }

    public void loadTable(){
        Client connectedClient = connectedUser.getUserConnect().getClient();
        if (connectedUser.getUserConnect().getUserRole() == UserRole.Client) {
            List<Paiement> Paiements = factoryService.getInstancePaiementService().getPaiementByClientId(connectedClient.getId());
            System.out.println(Paiements);
            ObservableList<Paiement>PaiementList=FXCollections.observableArrayList(Paiements);
            tabview.setItems(PaiementList);
        }else{
            List<Paiement> Paiements = factoryService.getInstancePaiementService().show();
            System.out.println(Paiements);
            ObservableList<Paiement>PaiementList=FXCollections.observableArrayList(Paiements);
            tabview.setItems(PaiementList);
        }
     
    };
    @FXML
    public void addPaiement() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/paiementForm.fxml"));
            Parent newContent = loader.load();

            // Récupérer le parent contenant `contentPane`
            Parent parentRoot = tabview.getScene().getRoot();
            VBox contentPane = (VBox) parentRoot.lookup("#contentPane");

            // Remplacer le contenu du panneau central
            if (contentPane != null) {
                contentPane.getChildren().setAll(newContent);
            } else {
                System.out.println("ContentPane non trouvé !");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception ici
            System.out.println("Erreur lors du chargement de la vue paiementForm.fxml");
        }
    }
    }





