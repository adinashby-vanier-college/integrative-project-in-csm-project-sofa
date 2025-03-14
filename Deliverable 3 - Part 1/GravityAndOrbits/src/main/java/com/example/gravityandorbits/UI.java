package com.example.gravityandorbits;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UI extends Parent {

    private int rowCount = 0;
    private Text warningMsg = new Text("Cannot add more than 5 planets.");
    MenuItem sun = new MenuItem("Sun");
    MenuItem earth = new MenuItem("Earth");
    MenuItem moon = new MenuItem("Moon");
    MenuItem mars = new MenuItem("Mars");
    MenuItem venus = new MenuItem("Venus");
    MenuItem neptune = new MenuItem("Neptune");

    public BorderPane initialize() {
        BorderPane root = new BorderPane();
        // MenuBar
        MenuBar menuBar = new MenuBar();
        Menu Exit = new Menu("Exit");
        MenuItem exit = new MenuItem("Exit the application");
        exit.setOnAction(e -> {
            exitButton();
        });
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
        Exit.getItems().addAll(exit);
        helpUserGuide.getItems().addAll(help, userGuide);

        menuBar.getMenus().addAll(Exit, file, settings, helpUserGuide);
        root.setTop(menuBar);

        // Left menu
        GridPane leftGrid = new GridPane();
        leftGrid.setAlignment(Pos.TOP_LEFT);
        leftGrid.setHgap(20);
        leftGrid.setVgap(20);

        // 1st button
        MenuButton selectPlanet1 = new MenuButton("Select Planet 1"); rowCount++;
        MenuItem sun1 = new MenuItem(sun.getText());
        MenuItem earth1 = new MenuItem(earth.getText());
        MenuItem moon1 = new MenuItem(moon.getText());
        MenuItem mars1 = new MenuItem(mars.getText());
        MenuItem venus1 = new MenuItem(venus.getText());
        MenuItem neptune1 = new MenuItem(neptune.getText());
        selectPlanet1.getItems().addAll(sun1, earth1, moon1, mars1, venus1, neptune1);

        // 2nd button
        MenuButton selectPlanet2 = new MenuButton("Select Planet 2"); rowCount++;
        MenuItem sun2 = new MenuItem(sun.getText());
        MenuItem earth2 = new MenuItem(earth.getText());
        MenuItem moon2 = new MenuItem(moon.getText());
        MenuItem mars2 = new MenuItem(mars.getText());
        MenuItem venus2 = new MenuItem(venus.getText());
        MenuItem neptune2 = new MenuItem(neptune.getText());
        selectPlanet2.getItems().addAll(sun2, earth2, moon2, mars2, venus2, neptune2);

        Button addCustomPlanet = new Button("Add Custom Planet");
        addCustomPlanet.setOnAction(e -> {
            if (rowCount < 5) {
                leftGrid.add(addCustomPlanet(rowCount), 1, rowCount);
            }
            rowCount++;
            if (rowCount > 5) {
                // Maximum reached
                if (!leftGrid.getChildren().contains(warningMsg)) {
                    leftGrid.add(warningMsg, 1, 7);
                    PauseTransition pause = new PauseTransition(Duration.seconds(3));
                    pause.setOnFinished(event -> {
                        leftGrid.getChildren().remove(warningMsg); // Remove the warning message after 3 seconds
                    });
                    pause.play();
                }
            }

        });

        leftGrid.add(selectPlanet1, 1, 0);
        leftGrid.add(selectPlanet2, 1 ,1);
        leftGrid.add(addCustomPlanet, 1, 8);
        root.setLeft(leftGrid);

        return root;
    }

    // Set the functionality for help button
    public void helpButton() {
        Stage stage = new Stage();
        Label helpText = new Label("Insert help text here");
        helpText.setScaleX(2);
        helpText.setScaleY(2);
        StackPane sp = new StackPane();
        sp.getChildren().addAll(helpText);
        Scene scene = new Scene(sp, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Help");
        stage.show();
    }

    // Set the functionality for user guide button
    public void userGuideButton() {
        Stage stage = new Stage();
        Label userGuideText = new Label("Insert user guide text here");
        userGuideText.setScaleX(2);
        userGuideText.setScaleY(2);
        StackPane sp = new StackPane();
        sp.getChildren().addAll(userGuideText);
        Scene scene = new Scene(sp, 500, 400);
        stage.setScene(scene);
        stage.setTitle("User Guide");
        stage.show();
    }

    // Set the functionality for exit button
    public void exitButton() {
        Platform.exit();
    }

    //Set the functionality for adding custom planets
    public MenuButton addCustomPlanet(int rowCount) {
        MenuButton selectPlanet = new MenuButton("Select Planet " + (rowCount+1));
        MenuItem sunX = new MenuItem(sun.getText());
        MenuItem earthX = new MenuItem(earth.getText());
        MenuItem moonX = new MenuItem(moon.getText());
        MenuItem marsX = new MenuItem(mars.getText());
        MenuItem venusX = new MenuItem(venus.getText());
        MenuItem neptuneX = new MenuItem(neptune.getText());
        selectPlanet.getItems().addAll(sunX, earthX, moonX, marsX, venusX, neptuneX);

        return selectPlanet;
    }

}
