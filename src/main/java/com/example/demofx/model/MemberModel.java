package com.example.demofx.model;

import com.example.demofx.db.DBConnection;
import com.example.demofx.to.Member;

import java.sql.*;
import java.util.ArrayList;

public class MemberModel {
    public static boolean SaveMember(Member member){
        //create connection
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
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
            Connection connection = DBConnection.getDbConnection().getConnection();
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

    public static Member searchMember(String id){
        //create connection
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("select * from member where mid=?");
            stm.setObject(1, id);

            ResultSet resultSet = stm.executeQuery();
            Member member = new Member();

            while(resultSet.next()){
                member.setName(resultSet.getString(2));
                member.setEmail(resultSet.getString(3));
                member.setAge((resultSet.getInt(4)));
                member.setLocation(resultSet.getString(5));
            }

            return member;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
