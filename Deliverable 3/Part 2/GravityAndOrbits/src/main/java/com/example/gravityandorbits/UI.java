package com.example.gravityandorbits;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

public class UI extends Parent {

    public final double SCREENWIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final double SCREENHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private int rowCount = 0;
    private final Text warningMsg = new Text("Cannot add more than 5 planets.");
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
            HelpInterface.LoadHelpInterface();
        });
        MenuItem userGuide = new MenuItem("User Guide");
        userGuide.setOnAction(e -> {
            userGuideButton();
        });
        Exit.getItems().addAll(exit);
        helpUserGuide.getItems().addAll(help, userGuide);

        menuBar.getMenus().addAll(Exit, file, settings, helpUserGuide);
        root.setTop(menuBar);

        // Left side
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

        Separator separator1 = new Separator();
        separator1.setScaleX(39);
        separator1.setScaleY(3);
        separator1.setLayoutY(370);

        Separator separator2 = new Separator();
        separator2.setScaleX(3);
        separator2.setOrientation(Orientation.VERTICAL);
        separator2.setLayoutX(200);
        separator2.setPrefHeight(SCREENHEIGHT);

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
        leftContainer.getChildren().addAll(topLeftGrid, separator1, separator2,
                textPreset, preset1, preset2, preset3);
        root.setLeft(leftContainer);

        // Right side

        Slider massMultiplier = new Slider(0.5, 3.5, 1);
        Slider radiusMultiplier = new Slider(0.5, 3.5, 1);
        massMultiplier.setShowTickLabels(true);
        massMultiplier.setMajorTickUnit(0.5);
        massMultiplier.setMinorTickCount(0);
        massMultiplier.setShowTickMarks(true);
        radiusMultiplier.setShowTickLabels(true);
        radiusMultiplier.setShowTickMarks(true);
        radiusMultiplier.setMajorTickUnit(0.5);
        radiusMultiplier.setMinorTickCount(0);

        Label planetName = new Label();
        planetName.setText("Planet's Parameters");
        Label velocity = new Label();
        velocity.setText("Velocity: 0 m/s");
        
        Label sliderName=new Label();
        sliderName.setText("Mass Multiplier");
        Label sliderName2= new Label();
        sliderName2.setText("Radius Multiplier");
        
        VBox MassMultiplier= new VBox(sliderName,massMultiplier);
        
        VBox RadiusMultiplier= new VBox(sliderName2,radiusMultiplier);
        
        VBox topRight = new VBox();
        topRight.setLayoutX(0);  //was 500
        topRight.setSpacing(35);
        topRight.setPadding(new Insets(25));
        /*topRight.getChildren().addAll(planetName, velocity,sliderName,
                massMultiplier, sliderName2,radiusMultiplier);*/
        topRight.getChildren().addAll(planetName, velocity,MassMultiplier,RadiusMultiplier);
        

        Separator separator3 = new Separator();
        separator3.setScaleX(33);
        separator3.setScaleY(3);
        separator3.setLayoutX(165);
        separator3.setLayoutY(370);

        Separator separator4 = new Separator();
        separator4.setOrientation(Orientation.VERTICAL);
        separator4.setScaleX(3);
        separator4.setPrefHeight(SCREENHEIGHT);
        separator4.setLayoutX(5);
        separator4.setLayoutY(0);

        CheckBox showPath = new CheckBox("Show Path");
        CheckBox showGVectors = new CheckBox("Show Gravity Vectors");
        CheckBox showVVectors = new CheckBox("Show Velocity Vectors");
        CheckBox showGrid = new CheckBox("Show Grid");
  
        Slider scale = new Slider();
        Slider time = new Slider();
        Label scaleLabel = new Label("Scale");
        Label timeLabel = new Label("Time");

        VBox bottomRight = new VBox();
        bottomRight.setSpacing(17);
        bottomRight.setLayoutX(20);
        bottomRight.setLayoutY(390);
        bottomRight.getChildren().addAll(showPath, showGVectors, showVVectors,
                showGrid, scaleLabel, scale, timeLabel, time);

        Button start = new Button("Start");
        Button pause = new Button("Stop");
        Button reset = new Button("Reset");

        start.setLayoutX(20);
        start.setLayoutY(670);
        pause.setLayoutX(70);
        pause.setLayoutY(670);
        reset.setLayoutX(120);
        reset.setLayoutY(670);

        Pane rightContainer = new Pane();
        rightContainer.getChildren().addAll(topRight, separator3, separator4,
                bottomRight, start, pause, reset);
        root.setRight(rightContainer);
        

        // Outer space (center)
        Canvas canvas = new Canvas(1520, 990);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        //To show grid
        showGrid.setOnAction(e->{
        if(showGrid.isSelected()){    
        gc.setStroke(new Color(1.0, 1.0, 1.0, 0.5));
        gc.setLineWidth(2);
        
        int gridSize=25;
            for (int x = 5; x <= canvas.getWidth(); x=x+gridSize) {
            gc.strokeLine(x, 0, x, canvas.getHeight());
        }
            
            for (int y = 5; y <= canvas.getHeight(); y=y+gridSize) {
            gc.strokeLine(0,y ,canvas.getWidth(),y );
            
        }
            
        }else{
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }
        });
        
        
        
        root.setCenter(canvas);

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
        text.setLayoutX(80);
        text.setLayoutY(40);
        text.setScaleX(1.2);
        text.setScaleY(1.2);
        yes.setLayoutX(100);
        yes.setLayoutY(130);
        yes.setScaleX(1.2);
        yes.setScaleY(1.2);
        yes.setPrefSize(50, 10);
        no.setLayoutX(270);
        no.setLayoutY(130);
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
