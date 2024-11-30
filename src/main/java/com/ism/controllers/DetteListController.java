package com.ism.controllers;

import java.io.IOException;
import java.util.List;

import com.ism.UserConnect;
import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Client;
import com.ism.data.entities.Dette;
import com.ism.data.enums.TypeDette;
import com.ism.data.enums.UserRole;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
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
    private UserConnect connectedUser;

    public void setConnectedUser(UserConnect connectedUser) {
        this.connectedUser = connectedUser;
    }

    public void initialize(){
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        montantRestant.setCellValueFactory(new PropertyValueFactory<>("montantRestant"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeDette.setCellValueFactory(new PropertyValueFactory<>("typeDette"));

        factoryService= new FactoryService();
       loadTable();

    }

    public void loadTable(){
        Client connectedClient = factoryService.getInstanceClientService().getConnectedClient(connectedUser.getUserConnect().getId());
        if (connectedClient.getUser().getUserRole() == UserRole.Client) {
            List<Dette> Dettes = factoryService.getInstanceDetteService().getByClientId(connectedClient.getId());
            System.out.println(Dettes);
            ObservableList<Dette>DetteList=FXCollections.observableArrayList(Dettes);
            tabview.setItems(DetteList);
        }else if (connectedClient.getUser().getUserRole() == UserRole.Admin || connectedClient.getUser().getUserRole() == UserRole.Boutiquier) {
            List<Dette> Dettes = factoryService.getInstanceDetteService().show();
            System.out.println(Dettes);
            ObservableList<Dette>DetteList=FXCollections.observableArrayList(Dettes);
            tabview.setItems(DetteList);
        }
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
    public void addDette() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/detteForm.fxml"));
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
            System.out.println("Erreur lors du chargement de la vue detteForm.fxml");
        }
    }
}






