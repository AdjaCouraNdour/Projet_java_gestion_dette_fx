package com.ism;

import com.ism.controllers.*;
import com.ism.core.dataSource.DataSourceImpl;
import com.ism.data.services.list.LoginService;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginScreen();
    }

    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/connexion.fxml"));
            Pane loginPane = loader.load();

            LoginController controller = loader.getController();
            controller.setApp(this);

            DataSourceImpl conn = new DataSourceImpl();
            LoginService loginService = new LoginService(conn);
            controller.setLoginService(loginService);

            Scene loginScene = new Scene(loginPane, 400, 300);
            loginScene.getStylesheets().add(getClass().getResource("/styles/login.css").toExternalForm());
            primaryStage.setScene(loginScene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour afficher la page principale avec barre de navigation
    public void showMainApp() {
        primaryStage.setTitle("Gestion Dettes");

        // Créer une barre de navigation supérieure
        HBox topNavBar = new HBox();
        topNavBar.getStyleClass().add("top-nav-bar");

        // Titre de l'application
        Label titleLabel = new Label("Gestion de Dette");
        titleLabel.getStyleClass().add("nav-title");

        // Bouton de déconnexion
        Button logoutButton = new Button("Déconnexion");
        logoutButton.getStyleClass().add("logout-button");
        logoutButton.setOnAction(event -> showLoginScreen());

        // Ajouter le titre et le bouton à la barre de navigation supérieure
        topNavBar.getChildren().addAll(titleLabel, logoutButton);
        HBox.setHgrow(titleLabel, Priority.ALWAYS); // Permet au titre de prendre tout l'espace disponible

        // Créer une VBox pour la barre de navigation gauche
        VBox leftNavBar = new VBox(10);
        leftNavBar.getStyleClass().add("nav-bar");

        // Créer des boutons de navigation
        Button clientsListButton = new Button("Liste des Clients"); // Nouveau bouton pour la liste des clients
        Button clientButton = new Button("Form Clients");
        Button detteButton = new Button("Form Dettes");

        // Ajouter des actions aux boutons
        clientButton.setOnAction(event -> showClientScreen());
        clientsListButton.setOnAction(event -> showClientsList()); // Action pour afficher la liste des clients
        detteButton.setOnAction(event -> showDetteScreen());

        // Ajouter les boutons à la barre de navigation gauche
        leftNavBar.getChildren().addAll(clientsListButton, clientButton, detteButton);

        // Configurer le layout principal
        rootLayout = new BorderPane();
        rootLayout.setTop(topNavBar); // Ajouter la barre de navigation supérieure
        rootLayout.setLeft(leftNavBar); // Ajouter la barre de navigation gauche

        // Afficher par défaut l'interface Client au centre
        showClientsList();

        // Créer la scène principale et l'afficher
        Scene mainScene = new Scene(rootLayout, 800, 600);
        mainScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    // Méthode pour afficher l'interface de gestion des clients
    public void showClientScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/Client.fxml"));
            Pane clientPane = loader.load();

            ClientController clientController = loader.getController(); // Récupérer le contrôleur Client

            // Appliquer le style CSS directement au clientPane
            clientPane.getStylesheets().add(getClass().getResource("/styles/client.css").toExternalForm());

            rootLayout.setCenter(clientPane); // Placer l'interface Client au centre

        } catch (Exception e) {
            e.printStackTrace(); // Gérer les exceptions d'affichage
        }
    }

    // Méthode pour afficher l'interface de gestion des dettes
    public void showDetteScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/Dette.fxml"));
            Pane dettePane = loader.load();

            rootLayout.setCenter(dettePane); // Placer l'interface Dette au centre

        } catch (Exception e) {
            e.printStackTrace(); // Gérer les exceptions d'affichage
        }
    }

    // Méthode pour afficher la liste des clients
    public void showClientsList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/clientList.fxml"));
            Pane clientsListPane = loader.load();

            ClientsListController controller = loader.getController();
            // Initialisation si nécessaire
            // controller.setSomeService(yourService); // Décommentez et utilisez cette ligne si besoin
            clientsListPane.getStylesheets().add(getClass().getResource("/styles/client.css").toExternalForm());

            rootLayout.setCenter(clientsListPane); // Placer la liste des clients au centre

        } catch (Exception e) {
            e.printStackTrace(); // Gérer les exceptions d'affichage
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
