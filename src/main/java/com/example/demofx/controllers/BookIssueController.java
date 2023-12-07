package com.example.demofx.controllers;

import com.example.demofx.HelloApplication;
import com.example.demofx.model.BookModel;
import com.example.demofx.model.MemberModel;
import com.example.demofx.to.Book;
import com.example.demofx.to.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BookIssueController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtBookIsbn;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtMemberName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtBookId;


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
    void bookSearch(ActionEvent event) {
        String id = txtBookId.getText();

        Book book = BookModel.searchBook(id);
        txtBookName.setText(book.getName());
        txtBookIsbn.setText(book.getIsbn());
        txtQuantity.setText(String.valueOf(book.getQty()));
        txtPrice.setText(String.valueOf(book.getPrice()));

    }

    @FXML
    void memberSearch(ActionEvent event) {
        String id = txtMemberId.getText();

        Member member = MemberModel.searchMember(id);
        txtMemberName.setText(member.getName());
        txtEmail.setText(member.getEmail());
        txtAge.setText(String.valueOf(member.getAge()));
        txtLocation.setText(member.getLocation());
    }

    @FXML
    void issueBook(ActionEvent event) {

    }
}
