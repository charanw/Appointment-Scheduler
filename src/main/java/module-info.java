module appointmentschedulerappfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens model to javafx.fxml;
    exports model;
    exports controller;
    opens controller to javafx.fxml;
    opens main to javafx.fxml;
    exports main;
}