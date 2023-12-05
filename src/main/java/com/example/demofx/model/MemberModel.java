package com.example.demofx.model;

import com.example.demofx.to.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberModel {
    public static boolean SaveMember(Member member){
        //create connection
        try {
            //register the Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create connection object
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apjd_book_store","root","Vira@95714");
            //create statement object
            PreparedStatement stm = connection.prepareStatement("insert into member(mid, member_name, email,age,address) values (?,?,?,?,?)");
            stm.setObject(1, member.getId());
            stm.setObject(2, member.getName());
            stm.setObject(3, member.getEmail());
            stm.setObject(4, member.getAge());
            stm.setObject(5, member.getLocation());

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

    public static boolean DeleteMember(Member member){
        //create database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apjd_book_store","root","Vira@95714");
            PreparedStatement stm = connection.prepareStatement("delete from member where mid=?");
            stm.setObject(1, member.getId());
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

    public static boolean UpdateMember(){
        return true;
    }

    public static ArrayList GetAllMember(){
        return null;
    }

}
