package com.example.model;

import controller.LoginFormController;
import dao.AppointmentQuery;
import dao.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Scheduling Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, SQLException {
        JDBC.openConnection();
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        AppointmentQuery.addAppointment("Test", "Test description", "location", "coffee break", start, end, 1, 2, 3);
        launch();
        JDBC.closeConnection();

    }
}