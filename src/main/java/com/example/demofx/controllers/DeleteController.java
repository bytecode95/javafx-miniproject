package com.example.demofx.controllers;

import com.example.demofx.HelloApplication;
import com.example.demofx.model.BookModel;
import com.example.demofx.to.Book;
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

        boolean execution = BookModel.DeleteBook(new Book(id));
        if(execution){
            System.out.println("Successfully deleted!..");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully deleted!");
            alert.show();
        }else{
            System.out.println("Process Failed!..");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Deletion Failed!");
            alert.show();
        }


    }


}
