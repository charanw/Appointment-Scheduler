module com.example.appointmentschedulerappfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.model to javafx.fxml;
    exports com.example.model;
    exports controller;
    opens controller to javafx.fxml;
}