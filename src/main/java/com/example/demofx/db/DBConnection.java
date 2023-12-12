package com.example.demofx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//singleton design pattern
public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;

    // Private constructor to prevent instantiation outside the class
    private DBConnection() throws ClassNotFoundException, SQLException {
        //load connector -- driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //create connection with database
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apjd_book_store", "root", "Vira@95714");
    }
    // Public method to get the singleton instance
    public static DBConnection getDbConnection() throws SQLException, ClassNotFoundException {
        if(dbConnection==null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
    // Public method to get the database connection
    public Connection getConnection(){
        return connection;
    }
}
