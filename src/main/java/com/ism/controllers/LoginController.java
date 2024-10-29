package com.ism.controllers;

import com.ism.App;
import com.ism.data.services.list.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    private App app;
    private LoginService loginService;

    @FXML
    public void initialize() {
        loginButton.setOnAction(event -> login());
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    private void login() {
        String login = usernameField.getText();
        String password = passwordField.getText();

        if (loginService.validateUser(login, password)) {
            app.showMainApp();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText(null);
            alert.setContentText("Nom d'utilisateur ou mot de passe incorrect");
            alert.showAndWait();
        }
    }
}
