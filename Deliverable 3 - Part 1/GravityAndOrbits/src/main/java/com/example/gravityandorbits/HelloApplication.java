package com.example.gravityandorbits;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Label label = new Label("Welcome to JavaFX.");
        label.setScaleX(2);
        label.setScaleY(2);
        StackPane sp = new StackPane(label);
        Scene scene = new Scene(/*fxmlLoader.load()*/ sp, 500, 400);
        stage.setTitle("Welcome to JavaFX!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}