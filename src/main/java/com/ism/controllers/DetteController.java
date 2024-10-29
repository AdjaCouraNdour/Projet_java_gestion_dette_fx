package com.ism.controllers;

import com.ism.data.entities.Article;
import com.ism.data.entities.Client;
import com.ism.data.entities.Details;
import com.ism.data.entities.Dette;
import com.ism.data.entities.User;
import com.ism.data.enums.TypeDette;
import com.ism.data.services.list.DetteService;
import com.ism.data.services.list.LoginService;
import com.ism.data.services.list.ArticleService;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetteController {

    private DetteService detteService;
    private ArticleService articleService;
    private LoginService loginService; // Service pour récupérer le client
    private User connectedUser; // Utilisateur connecté
    private ComboBox<Article> articleComboBox; // ComboBox pour sélectionner l'article
    private TextField quantityField; // Champ pour entrer la quantité
    private TextArea outputArea;
    private Button createDetteButton;

    public DetteController(DetteService detteService, ArticleService articleService, LoginService loginService, User connectedUser) {
        this.detteService = detteService;
        this.articleService = articleService;
        this.loginService = loginService;
        this.connectedUser = connectedUser; // Initialisation de l'utilisateur connecté
    }

    public VBox createInterface(Stage stage) {
        VBox layout = new VBox(10);

        // ComboBox pour la sélection de l'article
        articleComboBox = new ComboBox<>();
        articleComboBox.getItems().addAll(articleService.show()); // Remplir avec tous les articles

        // Champ pour entrer la quantité de l'article
        quantityField = new TextField();
        quantityField.setPromptText("Entrez la quantité");

        // Bouton pour créer la dette
        createDetteButton = new Button("Enregistrer ma dette");
        createDetteButton.setOnAction(e -> createDette());

        // Zone de sortie pour afficher les messages
        outputArea = new TextArea();
        outputArea.setEditable(false);

        // Ajouter les éléments au layout
        layout.getChildren().addAll(
            new Label("Sélectionnez un article :"), articleComboBox,
            new Label("Quantité :"), quantityField,
            createDetteButton,
            outputArea
        );

        return layout;
    }

    // Créer une dette
    private void createDette() {
        // Récupérer l'article sélectionné
        Article selectedArticle = articleComboBox.getValue();
        if (selectedArticle == null) {
            outputArea.appendText("Erreur : Aucun article sélectionné.\n");
            return;
        }

        // Vérifier et récupérer la quantité
        String quantityText = quantityField.getText();
        if (quantityText.isEmpty()) {
            outputArea.appendText("Erreur : Veuillez entrer une quantité.\n");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                outputArea.appendText("Erreur : La quantité doit être supérieure à 0.\n");
                return;
            }
        } catch (NumberFormatException e) {
            outputArea.appendText("Erreur : Veuillez entrer un nombre valide pour la quantité.\n");
            return;
        }

        // Récupérer le client connecté via l'utilisateur connecté
        Client connectedClient = loginService.getConnectedClient(connectedUser.getId()); // Utiliser l'ID de l'utilisateur
        if (connectedClient == null) {
            outputArea.appendText("Erreur : Aucun client associé à l'utilisateur connecté.\n");
            return;
        }

        // Calculer le montant total de la dette
        double montantTotal = quantity * selectedArticle.getPrix();

        // Appeler la méthode pour enregistrer la dette
        if (enregistrerDette(selectedArticle, connectedClient, quantity, montantTotal)) {
            outputArea.appendText("Dette créée avec succès.\n");
            clearFields();
        } else {
            outputArea.appendText("Erreur lors de la création de la dette.\n");
        }
    }

    // Méthode pour enregistrer la dette
    private boolean enregistrerDette(Article selectedArticle, Client connectedClient, int quantity, double montantTotal) {
        Dette dette = new Dette();

        // Configurer les propriétés de la dette
        dette.setMontant(montantTotal);
        dette.setClient(connectedClient); // Associer le client connecté à la dette
        Details details = new Details(); // Créer une nouvelle instance de Details
        details.setArticle(selectedArticle);
        details.setQteDette(quantity);
        dette.setListeDetails(details);
        details.setDette(dette);

        // Initialiser montantVerse et montantRestant
        dette.setMontantVerse(0);
        dette.setMontantRestant(montantTotal);
        dette.setTypeDette(TypeDette.nonSolde);

        // Enregistrer la dette dans le service
        return detteService.save(dette);
    }

    private void clearFields() {
        articleComboBox.setValue(null);
        quantityField.clear();
    }
}
