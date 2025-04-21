package com.example.gravityandorbits;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import javafx.stage.Screen;

public class Main extends Application {

    private Stage stage;
    
    @Override
    public void start(Stage stage) throws IOException {
        Login login = new Login();
        this.stage = stage;
        showLoginScreen();

    }

    private void showLoginScreen() {
        Login login = new Login();
        login.setOnLoginSuccess(() -> {
            //This will be called when login is successful
            showMainApplication();
        });

        Scene scene = new Scene(login.showLoginScreen());
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("Login to Gravity and Orbits");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        
        Settings settings= new Settings();
        settings.registerScene(scene);
    }

    private void showMainApplication() { 
        UI ui = new UI();
        ui.startTimer();
        stage.close();
        Scene scene = new Scene(ui.initialize());
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("Gravity and Orbits");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setMaxWidth(Screen.getPrimary().getBounds().getWidth());
        stage.setMaxHeight(Screen.getPrimary().getBounds().getHeight());
        stage.show();
        //To Change Settings
        Settings settings= new Settings();
        settings.registerScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}