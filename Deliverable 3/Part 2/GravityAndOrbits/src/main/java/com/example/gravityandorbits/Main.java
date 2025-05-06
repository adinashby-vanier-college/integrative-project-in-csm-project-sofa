package com.example.gravityandorbits;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import javafx.stage.Screen;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        UI ui = new UI();
        Login login = new Login();
        ui.startTimer();

        Scene scene = new Scene(login.login()); //To skip login screen, change this to "ui.initialize()"
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("Gravity and Orbits");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setMaxWidth(Screen.getPrimary().getBounds().getWidth());
        stage.setMaxHeight(Screen.getPrimary().getBounds().getHeight());
        stage.show();

        if (login.validateLogin()) {
            /*Scene */scene = new Scene(ui.initialize());
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            stage.setTitle("Gravity and Orbits");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setMaxWidth(Screen.getPrimary().getBounds().getWidth());
            stage.setMaxHeight(Screen.getPrimary().getBounds().getHeight());
            stage.show();
        }

        else {

        }
        /*
        Scene scene = new Scene(ui.initialize());
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("Gravity and Orbits");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setMaxWidth(Screen.getPrimary().getBounds().getWidth());
        stage.setMaxHeight(Screen.getPrimary().getBounds().getHeight());
        stage.show();

        */

        //To Change Settings
        Settings settings= new Settings();
        settings.registerScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}