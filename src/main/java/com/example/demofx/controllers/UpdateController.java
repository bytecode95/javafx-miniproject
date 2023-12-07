package com.example.demofx.controllers;

import com.example.demofx.HelloApplication;
import com.example.demofx.model.BookModel;
import com.example.demofx.to.Book;
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

public class UpdateController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtbookid;

    @FXML
    private TextField txtbookisbn;

    @FXML
    private TextField txtbookname;

    @FXML
    private TextField txtbookprice;

    @FXML
    private TextField txtbookqty;

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
    void search(ActionEvent event) {
        String id = txtbookid.getText();

        Book book = BookModel.searchBook(id);
        txtbookname.setText(book.getName());
        txtbookisbn.setText(book.getIsbn());
        txtbookqty.setText(String.valueOf(book.getQty()));
        txtbookprice.setText(String.valueOf(book.getPrice()));

    }

    @FXML
    void update(ActionEvent event) {

    }
}
