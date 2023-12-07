package com.example.demofx.controllers;

import com.example.demofx.HelloApplication;
import com.example.demofx.model.MemberModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class UpdateMemberController {
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

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/main-menu-view.fxml"));
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
    void search(ActionEvent event) {
        String id = txtMemberID.getText();
        MemberModel.searchMember(id);


    }

    @FXML
    void update(ActionEvent event) {
        //create connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apjd_book_store","root","Vira@95714");
            PreparedStatement stm = connection.prepareStatement("update member set member_name=?,  email=?, age=?, address=? where mid=?");
            stm.setObject(1, txtMemberName.getText());
            stm.setObject(2, txtMemberEmail.getText());
            stm.setObject(3, Integer.parseInt(txtMemberAge.getText()));
            stm.setObject(4, txtMemberAddress.getText());
            stm.setObject(5, txtMemberID.getText());

            int result = stm.executeUpdate();
            System.out.println(result);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
