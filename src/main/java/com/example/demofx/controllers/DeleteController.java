package com.example.demofx.controllers;

import com.example.demofx.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteController {
    @FXML
    private AnchorPane root;


    @FXML
    private TextField txtbookid;

    @FXML
    void back(MouseEvent event) {
        try {
            //catch stage
            Stage stage = (Stage) this.root.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/book-menu-view.fxml"));
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
    void delete(ActionEvent event) {
        String id = txtbookid.getText();
        System.out.println(id);


        //load connector
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Create connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apjd_book_store","root","Vira@95714");
            //create sql query
            PreparedStatement stm = connection.prepareStatement("delete from book where bid=?");
            stm.setObject(1, id);
            int result = stm.executeUpdate();
            System.out.println(result);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
