package com.example.demofx.model;

import com.example.demofx.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IssueModel {
    public static boolean issueBook(String bookId, String memberId){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String curDate = dateFormat.format(date);

        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            //disable auto commit feature
            connection.setAutoCommit(false);

            //set data into issue table
            PreparedStatement stm = connection.prepareStatement("insert into issue(bid, mid, issue_date) values (?,?,?)");
            stm.setObject(1, bookId);
            stm.setObject(2, memberId);
            stm.setObject(3, curDate);
            int executed1 = stm.executeUpdate();
            if(executed1>0){
                PreparedStatement stm2 = connection.prepareStatement("update book set quantity=quantity-1 where bid=?");
                stm2.setObject(1, bookId);
                int executed2 = stm2.executeUpdate();
                if(executed2>0){
                    //commit all changes
                    connection.commit();
                    //set default auto commit setting true
                    connection.setAutoCommit(true);
                    return true;
                }else{
                    //clear cache data in ram
                    connection.rollback();
                    //set default auto commit setting true
                    connection.setAutoCommit(true);
                    return false;

                }
            }else{
                //clear cache data in ram
                connection.rollback();
                //set default auto commit setting true
                connection.setAutoCommit(true);
                return false;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
