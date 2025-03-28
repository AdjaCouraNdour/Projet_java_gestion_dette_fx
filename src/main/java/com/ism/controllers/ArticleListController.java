package com.ism.controllers;

import java.io.IOException;
import java.util.List;

import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Article;
import com.ism.data.enums.EtatArticle;

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

public class ArticleListController {

    @FXML private TableView<Article>tabview ;
    @FXML private TableColumn<Article, String> libelle;
    @FXML private TableColumn<Article, String> qteStock;
    @FXML private TableColumn<Article, String> prix;
    @FXML private TableColumn<Article, EtatArticle> etatArticle;

    
    private FactoryService factoryService ;


    public void initialize(){
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        qteStock.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        etatArticle.setCellValueFactory(new PropertyValueFactory<>("etatArticle"));
        factoryService= new FactoryService();
        loadTable();
    }

    public void loadTable(){
        List<Article> articles = factoryService.getInstanceArticleService().show();
        System.out.println(articles);        
        ObservableList<Article>articleList=FXCollections.observableArrayList(articles);
        tabview.setItems(articleList);
    };
    
      @FXML
    public void addArticle() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ism/articleForm.fxml"));
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
            System.out.println("Erreur lors du chargement de la vue articleForm.fxml");
        }
    }
       
}






