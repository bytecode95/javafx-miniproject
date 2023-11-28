module com.example.demofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.demofx to javafx.fxml;
    exports com.example.demofx;
    exports com.example.demofx.controllers;
    opens com.example.demofx.controllers to javafx.fxml;
    exports com.example.demofx.tm;
    opens com.example.demofx.tm to javafx.fxml;
}