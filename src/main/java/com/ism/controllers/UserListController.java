package com.ism.controllers;

import java.io.IOException;
import java.util.List;

import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.User;
import com.ism.data.enums.UserRole;

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
public class UserListController {

    @FXML private TableView<User>tabview ;
    @FXML private TableColumn<User, String> login;
    @FXML private TableColumn<User, String> email;
    @FXML private TableColumn<User, Boolean> actif;
    @FXML private TableColumn<User, UserRole> userRole;
    // @FXML private TableColumn<User, Integer> id;
    private FactoryService factoryService ;


    public void initialize(){
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        userRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        actif.setCellValueFactory(new PropertyValueFactory<>("actif"));
        // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        factoryService= new FactoryService();
       loadTable();

    }

    public void loadTable(){
        List<User> Users = factoryService.getInstanceUserService().show();
        System.out.println(Users);
        ObservableList<User>UserList=FXCollections.observableArrayList(Users);
        tabview.setItems(UserList);
    };

      @FXML
        public void addUser() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/userForm.fxml"));
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