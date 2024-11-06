package com.ism.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Article;
import com.ism.data.enums.EtatArticle;

public class ArticleController {

    @FXML private TextField libelleField;
    @FXML private TextField prixField;
    @FXML private TextField qteStockField;
    @FXML private TextArea outputArea;
    @FXML private Button createArticleButton;
    @FXML private EtatArticle etatArticle;

    private FactoryService factoryService = new FactoryService();

    @FXML
    private void initialize() {
    }

    @FXML
    private void createArticle() {
        // Récupérer les données du formulaire
        String libelle = libelleField.getText();
        String prixString = prixField.getText();
        String qteStockString = qteStockField.getText();
        EtatArticle etatArticle = EtatArticle.Disponible;

        // Vérifier que les champs obligatoires ne sont pas vides
        if (libelle.isEmpty() || prixString.isEmpty() || qteStockString.isEmpty() || etatArticle == null) {
            outputArea.appendText("Tous les champs doivent être remplis.\n");
            return; // Sortir de la méthode si un champ est vide
        }

        // Convertir les valeurs numériques
        int prix;
        double qteStock;
        try {
            prix = Integer.parseInt(prixString);
            qteStock = Double.parseDouble(qteStockString);
        } catch (NumberFormatException e) {
            outputArea.appendText("Le prix et la quantité doivent être des nombres valides.\n");
            return;
        }

        // Créer un nouvel article avec les données fournies
        Article article = new Article();
        article.setLibelle(libelle);
        article.setPrix(prix);
        article.setQteStock(qteStock);

        if ( article.getQteStock()==0) {
            etatArticle = EtatArticle.Indisponible; 
        }
        article.setEtatArticle(etatArticle);

        // Enregistrer l'article
        boolean success = enregistrerArticle(article);

        if (success) {
            outputArea.appendText("Article créé avec succès.\n");
        } else {
            outputArea.appendText("Erreur lors de la création de l'article.\n");
        }

        // Réinitialiser les champs après la création
        clearFields();
    }

    private void clearFields() {
        libelleField.clear();
        prixField.clear();
        qteStockField.clear();
    }

    public boolean enregistrerArticle(Article article) {
        if (article == null) {
            return false;
        }

        // Utiliser le service pour enregistrer l'article dans la base de données
        factoryService.getInstanceArticleService().save(article);

        return true;
    }
}
