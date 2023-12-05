package com.example.demofx.controllers;

import com.example.demofx.HelloApplication;
import com.example.demofx.model.MemberModel;
import com.example.demofx.to.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;



public class SaveMemberController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtMemberAddress;

    @FXML
    private TextField txtMemberAge;

    @FXML
    private TextField txtMemberEmail;

    @FXML
    private TextField txtMemberID;

    @FXML
    private TextField txtMemberName;

    @FXML
    void back(MouseEvent event) {
        try {
            //catch stage
            Stage stage = (Stage) this.root.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/member-menu-view.fxml"));
            //scene load
            Scene scene = new Scene(fxmlLoader.load());

            //set scene to stage
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void save(ActionEvent event) {
        String id = txtMemberID.getText();
        String name = txtMemberName.getText();
        String email = txtMemberEmail.getText();
        int age = Integer.parseInt(txtMemberAge.getText());
        String location = txtMemberAddress.getText();

        boolean execution  = MemberModel.SaveMember(new Member(id, name, email, age, location));

        if(execution){
            System.out.println("Successfully added!..");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully added!");
            alert.show();
            clear();
        }else{
            System.out.println("Process Failed!..");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save Failed!");
            alert.show();
        }

    }

    private void clear() {
        txtMemberID.setText("");
        txtMemberName.setText("");
        txtMemberEmail.setText("");
        txtMemberAge.setText("");
        txtMemberAddress.setText("");
    }
}
