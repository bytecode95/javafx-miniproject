package com.example.demofx;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadController implements Initializable {

    @FXML
    private TableView<BookTM> tblBooks;

    @FXML
    void getall(ActionEvent event) {

    }

    //code for get details from db
    public ArrayList<BookTM> loadAll(){
        //load connector -- driver
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apjd_book_store", "root", "Vira@95714");
            //create sql query
            PreparedStatement stm = connection.prepareStatement("select * from book;");
            ResultSet resultSet = stm.executeQuery();

            ArrayList<BookTM> list = new ArrayList<>();

            while (resultSet.next()){
                BookTM tm = new BookTM();
                tm.setId(resultSet.getString(1));
                tm.setName(resultSet.getString(2));
                tm.setIsbn(resultSet.getString(3));
                tm.setQty(resultSet.getInt(4));
                tm.setPrice(resultSet.getDouble(5));

                list.add(tm);
            }
            return list;

        }catch(ClassNotFoundException | SQLException ex){
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tblBooks.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblBooks.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));

        ArrayList<BookTM> bookTMS = loadAll();

        tblBooks.setItems(FXCollections.observableArrayList(bookTMS));
    }
}
