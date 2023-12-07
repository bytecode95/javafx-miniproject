package com.example.demofx.model;
import com.example.demofx.db.DBConnection;
import com.example.demofx.to.Book;
import java.sql.*;
import java.util.ArrayList;



public class BookModel {
    public static boolean SaveBook(Book book){

        try{
            Connection connection = DBConnection.getDbConnection().getConnection();
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

    public static boolean DeleteBook(Book book){
        //load connector
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            //create sql query
            PreparedStatement stm = connection.prepareStatement("delete from book where bid=?");
            stm.setObject(1, book.getId());
            int result = stm.executeUpdate();
            if(result>0){
                return true;
            }else{
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static boolean UpdateBook(){
        return true;
    }

    public static ArrayList<Book> loadAllBook(){
        //load connector -- driver
        try{
            Connection connection = DBConnection.getDbConnection().getConnection();
            //create sql query
            PreparedStatement stm = connection.prepareStatement("select * from book;");
            ResultSet resultSet = stm.executeQuery();

            ArrayList<Book> list = new ArrayList<>();

            while (resultSet.next()){
                Book tm = new Book();
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

}
