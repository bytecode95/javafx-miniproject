package com.example.demofx.controllers;
import com.example.demofx.HelloApplication;
import com.example.demofx.model.BookModel;
import com.example.demofx.tm.BookTM;
import com.example.demofx.to.Book;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadController implements Initializable {
    @FXML
    private AnchorPane root;

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
    private TableView<BookTM> tblBooks;

    @FXML
    void getall(ActionEvent event) {
        ArrayList<Book> books = BookModel.loadAllBook();
        ArrayList<BookTM> bookTMS = new ArrayList<>();

        for(Book b: books){
            bookTMS.add(new BookTM(b.getId(), b.getName(), b.getIsbn(), b.getQty(), b.getPrice()));
        }

        tblBooks.setItems(FXCollections.observableArrayList(bookTMS));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tblBooks.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblBooks.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));

        ArrayList<Book> books = BookModel.loadAllBook();
        ArrayList<BookTM> bookTMS = new ArrayList<>();

        for(Book b: books){
            bookTMS.add(new BookTM(b.getId(), b.getName(), b.getIsbn(), b.getQty(), b.getPrice()));
        }

        tblBooks.setItems(FXCollections.observableArrayList(bookTMS));
    }
}
