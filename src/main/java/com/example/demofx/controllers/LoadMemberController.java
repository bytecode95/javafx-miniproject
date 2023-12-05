package com.example.demofx.controllers;

import com.example.demofx.HelloApplication;
import com.example.demofx.tm.MemberTM;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class LoadMemberController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<MemberTM> tblMember;

    @FXML
    void getAll(ActionEvent event) {

    }



    @FXML
    void back(MouseEvent event) {
        try {
            //catch stage
            Stage stage = (Stage) this.root.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/main-menu-view.fxml"));
            //scene load
            Scene scene = new Scene(fxmlLoader.load());

            //set scene to stage
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<MemberTM> loadAllMembers(){
        //load connector -- driver
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apjd_book_store", "root", "Vira@95714");
            //create sql query
            PreparedStatement stm = connection.prepareStatement("select * from member;");
            ResultSet resultSet = stm.executeQuery();


            ArrayList<MemberTM> list = new ArrayList<>();

            while (resultSet.next()){
                MemberTM tmMember = new MemberTM();
                tmMember.setId(resultSet.getString(1));
                tmMember.setName(resultSet.getString(2));
                tmMember.setEmail(resultSet.getString(3));
                tmMember.setAge(resultSet.getInt(4));
                tmMember.setLocation(resultSet.getString(5));

                list.add(tmMember);
            }
            return list;

        }catch(ClassNotFoundException | SQLException ex){
            throw new RuntimeException(ex);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblMember.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblMember.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblMember.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblMember.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("age"));
        tblMember.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("location"));

        ArrayList<MemberTM> memberTms = loadAllMembers();
        tblMember.setItems(FXCollections.observableArrayList(memberTms));
    }
}
