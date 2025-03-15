package com.example.gravityandorbits;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        userGuide.setOnAction(e -> {
            userGuideButton();
        });
        Exit.getItems().addAll(exit);
        helpUserGuide.getItems().addAll(help, userGuide);

        menuBar.getMenus().addAll(Exit, file, settings, helpUserGuide);
        root.setTop(menuBar);

        // Left menu
        GridPane topLeftGrid = new GridPane();
        topLeftGrid.setAlignment(Pos.TOP_LEFT);
        topLeftGrid.setGridLinesVisible(false);
        topLeftGrid.setHgap(20);
        topLeftGrid.setVgap(20);

        // 1st button
        MenuButton selectPlanet1 = new MenuButton("Select Planet 1"); rowCount++;
        MenuItem sun1 = new MenuItem(sun.getText());
        MenuItem earth1 = new MenuItem(earth.getText());
        MenuItem moon1 = new MenuItem(moon.getText());
        MenuItem mars1 = new MenuItem(mars.getText());
        MenuItem venus1 = new MenuItem(venus.getText());
        MenuItem neptune1 = new MenuItem(neptune.getText());
        selectPlanet1.getItems().addAll(sun1, earth1, moon1, mars1, venus1, neptune1);
        sun1.setOnAction(e -> selectPlanet1.setText(sun1.getText()));
        earth1.setOnAction(e -> selectPlanet1.setText(earth1.getText()));
        moon1.setOnAction(e -> selectPlanet1.setText(moon1.getText()));
        mars1.setOnAction(e -> selectPlanet1.setText(mars1.getText()));
        venus1.setOnAction(e -> selectPlanet1.setText(venus1.getText()));
        neptune1.setOnAction(e -> selectPlanet1.setText(neptune1.getText()));
        selectPlanet1.setMinSize(160, 10);

        // 2nd button
        MenuButton selectPlanet2 = new MenuButton("Select Planet 2"); rowCount++;
        MenuItem sun2 = new MenuItem(sun.getText());
        MenuItem earth2 = new MenuItem(earth.getText());
        MenuItem moon2 = new MenuItem(moon.getText());
        MenuItem mars2 = new MenuItem(mars.getText());
        MenuItem venus2 = new MenuItem(venus.getText());
        MenuItem neptune2 = new MenuItem(neptune.getText());
        selectPlanet2.getItems().addAll(sun2, earth2, moon2, mars2, venus2, neptune2);
        sun2.setOnAction(e -> selectPlanet2.setText(sun2.getText()));
        earth2.setOnAction(e -> selectPlanet2.setText(earth2.getText()));
        moon2.setOnAction(e -> selectPlanet2.setText(moon2.getText()));
        mars2.setOnAction(e -> selectPlanet2.setText(mars2.getText()));
        venus2.setOnAction(e -> selectPlanet2.setText(venus2.getText()));
        neptune2.setOnAction(e -> selectPlanet2.setText(neptune2.getText()));
        selectPlanet2.setMinSize(160, 10);

        Button addCustomPlanet = new Button("Add Custom Planet");
        addCustomPlanet.setOnAction(e -> {
            if (rowCount < 5) {
                topLeftGrid.add(addCustomPlanet(rowCount), 1, (rowCount+1));
            }
            rowCount++;
            if (rowCount > 5) {
                // Maximum reached
                if (!topLeftGrid.getChildren().contains(warningMsg)) {
                    topLeftGrid.add(warningMsg, 1, 7);
                    PauseTransition pause = new PauseTransition(Duration.seconds(3));
                    pause.setOnFinished(event -> {
                        topLeftGrid.getChildren().remove(warningMsg); // Remove the warning message after 3 seconds
                    });
                    pause.play();
                }
            }

        });

        topLeftGrid.add(selectPlanet1, 1, 1);
        topLeftGrid.add(selectPlanet2, 1 ,2);
        topLeftGrid.add(addCustomPlanet, 1, 9);

        Separator hSeparator = new Separator();
        hSeparator.setScaleX(39);
        hSeparator.setScaleY(3);
        hSeparator.setLayoutY(370);

        Separator vSeparator = new Separator();
        vSeparator.setScaleX(2);
        vSeparator.setOrientation(Orientation.VERTICAL);
        vSeparator.setLayoutX(200);
        vSeparator.setPrefHeight(710);

        Label textPreset = new Label("Select Preset");
        textPreset.setLayoutX(20);
        textPreset.setLayoutY(390);

        Rectangle preset1 = new Rectangle(180, 70);
        preset1.setFill(Color.TRANSPARENT);
        preset1.setStroke(Color.BLACK);
        preset1.setStrokeWidth(0.7);
        preset1.setLayoutX(10);
        preset1.setLayoutY(420);
        preset1.setOnMouseEntered(event -> {
            preset1.getScene().setCursor(Cursor.HAND); // Set cursor to hand
        });
        preset1.setOnMouseExited(event -> {
            preset1.getScene().setCursor(Cursor.DEFAULT); // Return cursor to default
        });
        preset1.setOnMouseClicked(e-> {
            // Add functionality here
        });

        Rectangle preset2 = new Rectangle(180, 70);
        preset2.setFill(Color.TRANSPARENT);
        preset2.setStroke(Color.BLACK);
        preset2.setStrokeWidth(0.7);
        preset2.setLayoutX(10);
        preset2.setLayoutY(510);
        preset2.setOnMouseEntered(event -> {
            preset2.getScene().setCursor(Cursor.HAND); // Set cursor to hand
        });
        preset2.setOnMouseExited(event -> {
            preset2.getScene().setCursor(Cursor.DEFAULT); // Return cursor to default
        });
        preset2.setOnMouseClicked(e-> {
            // Add functionality here
        });

        Rectangle preset3 = new Rectangle(180, 70);
        preset3.setFill(Color.TRANSPARENT);
        preset3.setStroke(Color.BLACK);
        preset3.setStrokeWidth(0.7);
        preset3.setLayoutX(10);
        preset3.setLayoutY(600);
        preset3.setOnMouseEntered(event -> {
            preset3.getScene().setCursor(Cursor.HAND); // Set cursor to hand
        });
        preset3.setOnMouseExited(event -> {
            preset3.getScene().setCursor(Cursor.DEFAULT); // Return cursor to default
        });
        preset3.setOnMouseClicked(e-> {
            // Add functionality here
        });

        Pane leftContainer = new Pane();
        leftContainer.getChildren().addAll(topLeftGrid, hSeparator, vSeparator,
                textPreset, preset1, preset2, preset3);
        root.setLeft(leftContainer);

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
        Stage stage = new Stage();
        Pane pane = new Pane();
        Text text = new Text("Are you sure you want to exit the application?");
        Button yes = new Button("Yes");
        Button no = new Button("No");
        text.setLayoutX(70);
        text.setLayoutY(40);
        text.setScaleX(1.2);
        text.setScaleY(1.2);
        yes.setLayoutX(100);
        yes.setLayoutY(100);
        yes.setScaleX(1.2);
        yes.setScaleY(1.2);
        yes.setPrefSize(50, 10);
        no.setLayoutX(260);
        no.setLayoutY(100);
        no.setScaleX(1.2);
        no.setScaleY(1.2);
        no.setPrefSize(50, 10);
        pane.getChildren().addAll(text, yes, no);
        yes.setOnAction(e -> {
            Platform.exit();
        });
        no.setOnAction(e -> {
            stage.close();
        });
        Scene scene = new Scene(pane, 400, 200);
        stage.setTitle("Confirm exit");
        stage.setScene(scene);
        stage.show();
    }

    //Set the functionality for adding custom planets
    public MenuButton addCustomPlanet(int rowCount) {
        MenuButton selectPlanetX = new MenuButton("Select Planet " + (rowCount+1));
        MenuItem sunX = new MenuItem(sun.getText());
        MenuItem earthX = new MenuItem(earth.getText());
        MenuItem moonX = new MenuItem(moon.getText());
        MenuItem marsX = new MenuItem(mars.getText());
        MenuItem venusX = new MenuItem(venus.getText());
        MenuItem neptuneX = new MenuItem(neptune.getText());
        selectPlanetX.getItems().addAll(sunX, earthX, moonX, marsX, venusX, neptuneX);
        sunX.setOnAction(e -> selectPlanetX.setText(sunX.getText()));
        earthX.setOnAction(e -> selectPlanetX.setText(earthX.getText()));
        moonX.setOnAction(e -> selectPlanetX.setText(moonX.getText()));
        marsX.setOnAction(e -> selectPlanetX.setText(marsX.getText()));
        venusX.setOnAction(e -> selectPlanetX.setText(venusX.getText()));
        neptuneX.setOnAction(e -> selectPlanetX.setText(neptuneX.getText()));
        selectPlanetX.setMinSize(160, 10);

        return selectPlanetX;
    }

}
