package com.example.gravityandorbits;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UI extends Parent {

    public BorderPane initialize() {
        BorderPane root = new BorderPane();
        MenuBar menuBar = new MenuBar();
        Menu exit = new Menu("Exit");
        Menu file = new Menu("File");
        Menu settings = new Menu("Settings");
        Menu helpUserGuide = new Menu("Help/User Guide");
        MenuItem help = new MenuItem("Help");
        help.setOnAction(e -> {
            helpButton();
        });
        MenuItem userGuide = new MenuItem("User Guide");
        userGuide.setOnAction(e1 -> {
            userGuideButton();
        });
        helpUserGuide.getItems().addAll(help, userGuide);

        Label label = new Label("Welcome to JavaFX.");
        label.setScaleX(2);
        label.setScaleY(2);

        menuBar.getMenus().addAll(exit, file, settings, helpUserGuide);
        root.setTop(menuBar);
        root.getChildren().addAll(label);

        return root;
    }

    public void helpButton() {
        Stage stage = new Stage();
        StackPane sp = new StackPane();
        Label text = new Label("Insert help text here");
        text.setScaleX(2);
        text.setScaleY(2);
        sp.getChildren().addAll(text);
        Scene scene = new Scene(sp, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Help");
        stage.show();
    }

    public void userGuideButton() {
        Stage stage = new Stage();
        StackPane sp = new StackPane();
        Label text = new Label("Insert user guide text here");
        text.setScaleX(2);
        text.setScaleY(2);
        sp.getChildren().addAll(text);
        Scene scene = new Scene(sp, 500, 400);
        stage.setScene(scene);
        stage.setTitle("User Guide");
        stage.show();
    }

}
