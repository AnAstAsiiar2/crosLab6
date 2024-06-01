module com.example.paymentdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sitesdb to javafx.fxml;
    exports com.example.sitesdb;
    exports com.example.sitesdb.controller;
    opens com.example.sitesdb.controller to  javafx.fxml;
}