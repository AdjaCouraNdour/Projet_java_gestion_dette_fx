package com.ism.controllers;

import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Details;
import com.ism.data.entities.Article;
import com.ism.data.enums.EtatArticle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.List;
import java.util.function.Consumer;

public class DetailsController {

    @FXML private ComboBox<Article> articleComboBox; // Pour sélectionner un article
    @FXML private TextField qteField; // Champ pour la quantité
    @FXML private Button createDetailsButton; // Bouton pour créer les détails
    @FXML private TextArea outputArea; // Zone pour afficher les messages
    private Consumer<Details> onDetailsCreated; // Callback pour actions après création
    private FactoryService factoryService = new FactoryService(); // Service pour les opérations

    @FXML
    private void initialize() {
        // Chargement initial des articles disponibles
        loadAvailableArticles();
    }

    private void loadAvailableArticles() {
        // Récupérer les articles disponibles et les ajouter au ComboBox
        List<Article> articles = factoryService.getInstanceArticleService().getByArticleEtat(EtatArticle.Disponible);
        articleComboBox.getItems().addAll(articles);
    }

    @FXML
    private void createDetails() {
        // Récupérer les données du formulaire
        Article selectedArticle = articleComboBox.getValue();
        String qteText = qteField.getText();

        // Vérifier que l'article est sélectionné et que la quantité est valide
        if (selectedArticle == null) {
            outputArea.appendText("Erreur : veuillez sélectionner un article.\n");
            return;
        }

        if (qteText.isEmpty()) {
            outputArea.appendText("Erreur : la quantité ne peut pas être vide.\n");
            return;
        }

        try {
            int qte = Integer.parseInt(qteText);
            if (qte <= 0 || qte > selectedArticle.getQteStock()) {
                outputArea.appendText("Erreur : la quantité doit être supérieure à 0 et inférieure ou égale au stock disponible.\n");
                return;
            }

            // Créer les détails
            Details details = new Details();
            details.setArticle(selectedArticle);
            details.setQteDette(qte);

            // Enregistrer les détails
            enregistrerDetails(details);

            // Appeler le callback si défini
            if (onDetailsCreated != null) {
                onDetailsCreated.accept(details);
            }

            outputArea.appendText("Détails créés avec succès.\n");

        } catch (NumberFormatException e) {
            outputArea.appendText("Erreur : veuillez entrer une quantité valide.\n");
        }

        // Réinitialiser les champs après la création
        clearFields();
    }

    private void clearFields() {
        articleComboBox.setValue(null);
        qteField.clear();
    }

    public boolean enregistrerDetails(Details details) {
        if (details == null) {
            return false; 
        }

        factoryService.getInstanceDetailsService().save(details); // Appeler le service pour sauvegarder les détails
        return true; 
    }

    public void setOnDetailsCreated(Consumer<Details> onDetailsCreated) {
        this.onDetailsCreated = onDetailsCreated;
    }
}
