package com.example.model;

import helper.JDBC;
import helper.userQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Scheduling Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();

    }
}