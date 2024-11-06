package com.ism.controllers;

import java.util.List;

import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Paiement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class PaiementListController {

    @FXML private TableView<Paiement>tabview ;
    @FXML private TableColumn<Paiement, String> nom;
    @FXML private TableColumn<Paiement, String> prenom;
    @FXML private TableColumn<Paiement, String> adresse;
    @FXML private TableColumn<Paiement, Integer> id;
    private FactoryService factoryService ;


    public void initialize(){
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        factoryService= new FactoryService();
       loadTable();

    }

    public void loadTable(){
        List<Paiement> Paiements = factoryService.getInstancePaiementService().show();
        System.out.println("zerfgffnjj");
        System.out.println(Paiements);
        System.out.println("zerfgffnjj");
        ObservableList<Paiement>PaiementList=FXCollections.observableArrayList(Paiements);
        tabview.setItems(PaiementList);
    };
}






