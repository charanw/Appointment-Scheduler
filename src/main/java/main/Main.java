package main;

import dao.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    /**Starts the application. Loads the login form, sets the size of the window, and sets the title of the window. If the system default language is france, it translates the title.
     * @param stage the stage of the application
     * */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(getClass().getResource("/view/LoginForm.fxml")), 600, 400);

        if(Locale.getDefault().getLanguage().equals("fr")) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("localization/Nat", Locale.getDefault());
            stage.setTitle(resourceBundle.getString("applicationTitle"));
        } else {
            stage.setTitle("Scheduling Application");
        }
        stage.setScene(scene);
        stage.show();
    }
    /**The main method of the application. Connects to the database using the JDBC class, and then launches the application. Once the application is exited, it closes the connection to the database.
     * @param args stores Java command line arguments
     * */
    public static void main(String[] args) {
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}