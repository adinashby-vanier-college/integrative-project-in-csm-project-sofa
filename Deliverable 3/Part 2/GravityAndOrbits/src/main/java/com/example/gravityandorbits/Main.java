package com.example.gravityandorbits;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.Screen;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        UI ui = new UI();
        Scene scene = new Scene(ui.initialize());
        scene.getStylesheets().add("style.css");
        stage.setTitle("Gravity and Orbits");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setMaxWidth(Screen.getPrimary().getBounds().getWidth());
        stage.setMaxHeight(Screen.getPrimary().getBounds().getHeight());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
