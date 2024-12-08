package com.ism.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Paiement;
import com.ism.data.enums.TypeDette;
import com.ism.data.entities.Client;
import com.ism.data.entities.Dette;
import java.time.LocalDate;
import java.util.List;


public class PaiementController {

    @FXML private DatePicker dateField;
    @FXML private TextField montantField;
    @FXML private ComboBox<Dette> detteComboBox;
    @FXML private ComboBox<Client> clientComboBox;

    @FXML private Button createPaiementButton;
    @FXML private TextArea outputArea;

    private FactoryService factoryService = new FactoryService();
    
    @FXML
    private void initialize() {
        // Charger les clients dans la ComboBox client
        List<Client> clients = factoryService.getInstanceClientService().show(); 
        if (clients != null && !clients.isEmpty()) {
            clientComboBox.getItems().setAll(clients);
        } else {
            outputArea.appendText("Aucun client trouvé.\n");
        }
    
        // Listener pour mettre à jour la ComboBox dette en fonction du client sélectionné
        clientComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Charger les dettes du client sélectionné
                List<Dette> dettes = newValue.getListeDette(); 
                if (dettes != null && !dettes.isEmpty()) {
                    detteComboBox.getItems().setAll(dettes);
                } else {
                    detteComboBox.getItems().clear();
                    outputArea.appendText("Ce client n'a aucune dette.\n");
                }
            } else {
                detteComboBox.getItems().clear();
            }
        });
    }
    

    @FXML
    private void createPaiement() {
        LocalDate date = dateField.getValue();
        double montant = Double.parseDouble(montantField.getText());
        Client client = clientComboBox.getValue();

        if (client == null) {
            outputArea.appendText("Veuillez sélectionner un client .\n");
            return;
        }

        Dette dette = detteComboBox.getValue();

        if (dette == null) {
            outputArea.appendText("Veuillez sélectionner une dette.\n");
            return;
        }

       
        Paiement paiement = new Paiement();
        paiement.setDate(date);
        paiement.setMontant(montant);
        paiement.setDette(dette);

        
        // Enregistrer le paiement
        boolean success = enregistrerPaiement(paiement);

        if (success) {
            outputArea.appendText("Paiement créé avec succès.\n");
        } else {
            outputArea.appendText("Erreur lors de la création du paiement.\n");
        }

        clearFields();
    }

    private boolean enregistrerPaiement(Paiement paiement) {
        if (paiement == null) {
            return false;
        }
        factoryService.getInstancePaiementService().save(paiement);
        Dette dette = detteComboBox.getValue();
        dette.setMontantVerse(dette.getMontantVerse()+paiement.getMontant());
        dette.setMontantRestant(dette.getMontant()-dette.getMontantVerse());
        if (dette.getMontantVerse() == dette.getMontant()) {
            dette.setTypeDette(TypeDette.Solde);
        }
        factoryService.getInstanceDetteService().update(dette);
        return true;
    }

    private void clearFields() {
        dateField.setValue(LocalDate.now());
        montantField.clear();
        detteComboBox.setValue(null);
    }
}
