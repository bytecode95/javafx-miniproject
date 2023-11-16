package com.example.demofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveController {
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
    void save(ActionEvent event) throws ClassNotFoundException {
        String id = txtbookid.getText();
        String name = txtbookname.getText();
        String isbn = txtbookisbn.getText();
        int  qty = Integer.parseInt(txtbookqty.getText());
        double price = Double.parseDouble(txtbookprice.getText());


        //load connector -- driver
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apjd_book_store", "root", "Vira@95714");
            //create sql query
            PreparedStatement stm = connection.prepareStatement("insert into book values(?,?,?,?,?)");
            stm.setObject(1, id);
            stm.setObject(2,name);
            stm.setObject(3, isbn);
            stm.setObject(4, qty);
            stm.setObject(5, price);

            int result = stm.executeUpdate();
            if(result>0){
                System.out.println("Successfully added!..");
            }else{
                System.out.println("Process Failed!..");
            }

        }catch(ClassNotFoundException | SQLException ex){
            throw new RuntimeException(ex);
        }

    }
}
