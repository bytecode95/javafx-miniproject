package com.example.demofx.controllers;

import com.example.demofx.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;



    @FXML
    void login(ActionEvent event) {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        System.out.println(userName +" " +password);


        try {
            //catch stage
            Stage stage = (Stage) this.root.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
            //scene load
            Scene scene = new Scene(fxmlLoader.load());

            //set scene to stage
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
