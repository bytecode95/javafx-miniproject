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
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class SaveController {
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
    void cancel(ActionEvent event) {
        System.exit(0);
    }

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
    void save(ActionEvent event)  {
        String id = txtbookid.getText();
        String name = txtbookname.getText();
        String isbn = txtbookisbn.getText();
        int  qty = Integer.parseInt(txtbookqty.getText());
        double price = Double.parseDouble(txtbookprice.getText());

        boolean b = BookModel.SaveBook(new Book(id, name, isbn, qty, price));
        if(b){
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

    public void clear(){
        txtbookid.setText("");
        txtbookname.setText("");
        txtbookisbn.setText("");
        txtbookprice.setText("");
        txtbookqty.setText("");
    }

}
