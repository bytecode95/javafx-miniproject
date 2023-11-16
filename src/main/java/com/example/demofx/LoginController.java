package com.example.demofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void onLogin(ActionEvent event) {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        System.out.println(userName +" " +password);
    }
}
