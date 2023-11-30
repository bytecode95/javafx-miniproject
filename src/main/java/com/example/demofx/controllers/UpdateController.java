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
        System.out.println(id);

        //load connector -- driver
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apjd_book_store", "root", "Vira@95714");
            //create sql query
            PreparedStatement stm = connection.prepareStatement("select * from book where bid=?");
            stm.setObject(1, id);


            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()){
                txtbookname.setText(resultSet.getString(2));
                txtbookisbn.setText(resultSet.getString(3));
                txtbookqty.setText(String.valueOf(resultSet.getInt(4)));
                txtbookprice.setText(String.valueOf(resultSet.getDouble(5)));
            }

        }catch(ClassNotFoundException | SQLException ex){
            throw new RuntimeException(ex);
        }


    }

    @FXML
    void update(ActionEvent event) {
        //load connector -- driver
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apjd_book_store", "root", "Vira@95714");
            //create sql query
            PreparedStatement stm = connection.prepareStatement("update book set name=?, isbn=?,quantity=?, price=? where bid=?");
            stm.setObject(1, txtbookname.getText());
            stm.setObject(2, txtbookisbn.getText());
            stm.setObject(3, Integer.parseInt(txtbookqty.getText()));
            stm.setObject(4, Double.parseDouble(txtbookprice.getText()));
            stm.setObject(5, txtbookid.getText());

            int executed = stm.executeUpdate();
            System.out.println(executed);

        }catch(ClassNotFoundException | SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}
