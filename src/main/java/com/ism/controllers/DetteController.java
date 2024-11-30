package com.ism.controllers;

import com.ism.UserConnect;
import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Article;
import com.ism.data.entities.Client;
import com.ism.data.entities.Details;
import com.ism.data.entities.Dette;
import com.ism.data.enums.TypeDette;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class DetteController {

    FactoryService factoryService =new FactoryService();
    private UserConnect connectedUser;

    private ObservableList<Details> addedArticles = FXCollections.observableArrayList();

    @FXML private ComboBox<Article> articleComboBox;
    @FXML private TextField quantityField;
    @FXML private TableView<Details> tabview;
    @FXML private TableColumn<Details, String> libelleColumn;
    @FXML private TableColumn<Details, Integer> qteDetteColumn;
    @FXML private TableColumn<Details, Double> montantColumn;
    @FXML private Button addArticleButton;
    @FXML private Button createDetteButton;
    @FXML private TextArea outputArea;


    public void setConnectedUser(UserConnect connectedUser) {
        this.connectedUser = connectedUser;
    }

    public DetteController() {
    }
    @FXML
    public void initialize() {
        // Initialiser la ComboBox avec la liste des articles
        articleComboBox.getItems().addAll(factoryService.getInstanceArticleService().show());

        // Configurer les colonnes du tableau
        libelleColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getArticle().getLibelle())
        );
        qteDetteColumn.setCellValueFactory(new PropertyValueFactory<>("qteDette"));
        montantColumn.setCellValueFactory(cellData ->
            new SimpleDoubleProperty(cellData.getValue().getArticle().getPrix() * cellData.getValue().getQteDette()).asObject()
        );

        // Lier la liste observable au tableau
        tabview.setItems(addedArticles);

        // Associer les actions aux boutons
        addArticleButton.setOnAction(e -> addArticleToTable());
        createDetteButton.setOnAction(e -> createDette());
    }

    @FXML
    private void addArticleToTable() {
        Article selectedArticle = articleComboBox.getValue();
        if (selectedArticle == null) {
            outputArea.appendText("Erreur : Aucun article sélectionné.\n");
            return;
        }

        String quantityText = quantityField.getText();
        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                outputArea.appendText("Erreur : La quantité doit être supérieure à 0.\n");
                return;
            } else if (quantity > selectedArticle.getQteStock()) {
                outputArea.appendText("Erreur : Quantité supérieure au stock disponible.\n");
                return;
            }
        } catch (NumberFormatException e) {
            outputArea.appendText("Erreur : Quantité invalide.\n");
            return;
        }

        // Créer un nouvel objet Details pour l'article sélectionné
        Details detail = new Details();
        detail.setArticle(selectedArticle);
        detail.setQteDette(quantity);
        addedArticles.add(detail);
       
        // Effacer les champs après ajout
        articleComboBox.setValue(null);
        quantityField.clear();
    }
    @FXML   
    private void createDette() {
        Client connectedClient = factoryService.getInstanceClientService().getConnectedClient(connectedUser.getUserConnect().getId());
        if (connectedClient == null) {
            outputArea.appendText("Erreur : Aucun client associé à l'utilisateur connecté.\n");
            return;
        }

        if (addedArticles.isEmpty()) {
            outputArea.appendText("Erreur : Aucun article ajouté pour la dette.\n");
            return;
        }

        double montantTotal = addedArticles.stream()
                                           .mapToDouble(d -> d.getArticle().getPrix() * d.getQteDette())
                                           .sum();

        Dette dette = new Dette();
        dette.setClient(connectedClient);
        dette.setMontant(montantTotal);
        dette.setMontantVerse(0);
        dette.setMontantRestant(montantTotal);
        dette.setTypeDette(TypeDette.nonSolde);

        // Associer les détails à la dette
        for (Details detail : addedArticles) {
            detail.setDette(dette);
        }
        dette.setListeDetails(addedArticles);

        // enregistrerDette(dette);

        if (enregistrerDette(dette)) {
            outputArea.appendText("Dette créée avec succès.\n");
            addedArticles.clear();
            tabview.getItems().clear();
        } else {
            outputArea.appendText("Erreur lors de la création de la dette.\n");
        }
    }
    public boolean enregistrerDette(Dette dette) {
        if (dette == null) {
            return false;
        }
        factoryService.getInstanceDetteService().save(dette);
        for (Details detail : dette.getListeDetails()) {
            Article article = detail.getArticle();
            article.setQteStock(article.getQteStock() - detail.getQteDette());
            factoryService.getInstanceArticleService().update(article);
        }
    
        return true;
    }
    
}
