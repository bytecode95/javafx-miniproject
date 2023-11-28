package com.example.demofx.model;

import com.example.demofx.to.Book;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookModel {
    public static boolean SaveBook(Book book){
        //load connector -- driver
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apjd_book_store", "root", "Vira@95714");
            //create sql query
            PreparedStatement stm = connection.prepareStatement("insert into book values(?,?,?,?,?)");
            stm.setObject(1, book.getId());
            stm.setObject(2,book.getName());
            stm.setObject(3, book.getIsbn());
            stm.setObject(4, book.getQty());
            stm.setObject(5, book.getPrice());

            int result = stm.executeUpdate();
            if(result>0){
                return true;
            }else{
                return false;
            }

        }catch(ClassNotFoundException | SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public static boolean DeleteBook(){
        return true;
    }

    public static boolean UpdateBook(){
        return true;
    }


}
