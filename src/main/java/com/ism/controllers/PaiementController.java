package com.ism.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.ism.core.Factory.FactoryService;
import com.ism.data.entities.Paiement;
import com.ism.data.entities.Dette;

import java.time.LocalDate;

public class PaiementController {

    @FXML private DatePicker dateField;
    @FXML private TextField montantField;
    @FXML private ComboBox<Dette> detteComboBox;
    @FXML private Button createPaiementButton;
    @FXML private TextArea outputArea;

    private FactoryService factoryService = new FactoryService();

    @FXML
    private void initialize() {
        dateField.setValue(LocalDate.now());
        loadDettes(); // Charger les dettes dans le ComboBox
    }

    private void loadDettes() {
        // Remplir le ComboBox avec les dettes disponibles
        detteComboBox.getItems().addAll(factoryService.getInstanceDetteService().show());
    }

    @FXML
    private void createPaiement() {
        LocalDate date = dateField.getValue();
        double montant = Double.parseDouble(montantField.getText());
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
        return true;
    }

    private void clearFields() {
        dateField.setValue(LocalDate.now());
        montantField.clear();
        detteComboBox.setValue(null);
    }
}
