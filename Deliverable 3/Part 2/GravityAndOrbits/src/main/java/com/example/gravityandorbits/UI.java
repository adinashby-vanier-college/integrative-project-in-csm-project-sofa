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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;

import java.awt.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UI extends Parent {

    public final double SCREENWIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final double SCREENHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private static final double G = 6.6743e-11;
    private String selectedPlanet1 = null;
    private String selectedPlanet2 = null;
    private String selectedPlanetType = null;
    private int rowCount = 0;
    private final Text warningMsg = new Text("Cannot add more than 5 planets.");
    MenuItem sun = new MenuItem("Sun");
    MenuItem earth = new MenuItem("Earth");
    MenuItem moon = new MenuItem("Moon");
    MenuItem mars = new MenuItem("Mars");
    MenuItem venus = new MenuItem("Venus");
    MenuItem neptune = new MenuItem("Neptune");



    public BorderPane initialize() {
        Main main = new Main();
        BorderPane root = new BorderPane();
        // MenuBar
        MenuBar menuBar = new MenuBar();
        Menu Exit = new Menu("Exit");
        MenuItem exit = new MenuItem("Exit the application");
        exit.setOnAction(e -> {
            exitButton();
        });
        Menu file = new Menu("File");
        MenuItem save = new MenuItem("Save");
        MenuItem load = new MenuItem("Load");
        MenuItem delete = new MenuItem("Delete");
        save.setOnAction(e-> {
            //Add func.
        });
        load.setOnAction(e-> {
            FileManager.loadAnimation(new Stage());
        });
        delete.setOnAction(e-> {
            FileManager.deleteAnimation(new Stage());
        });
        file.getItems().addAll(save, load, delete);
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
        topLeftGrid.setVgap(/*20*/45);

        ToggleGroup toggleGroup = new ToggleGroup();

        Button addCustomPlanet = new Button("Add Custom Planet");
        addCustomPlanet.setOnAction(e -> {
            if (rowCount < 5) {
                addPlanet(topLeftGrid, toggleGroup, 1,rowCount + 1);
            }
            rowCount++;
            if (rowCount > 5) {
                // Maximum reached
                if (!topLeftGrid.getChildren().contains(warningMsg)) {
                    topLeftGrid.add(warningMsg, 1, /*7*/6);
                    PauseTransition pause = new PauseTransition(Duration.seconds(3));
                    pause.setOnFinished(event -> {
                        topLeftGrid.getChildren().remove(warningMsg); // Remove the warning message after 3 seconds
                    });
                    pause.play();
                }
            }

        });
        topLeftGrid.add(addCustomPlanet, 1,/*9*/7);

        Separator separator1 = new Separator();
        separator1.setScaleX(39);
        separator1.setScaleY(3);
        separator1.setLayoutY(/*370*/510);

        Separator separator2 = new Separator();
        separator2.setScaleX(3);
        separator2.setOrientation(Orientation.VERTICAL);
        separator2.setLayoutX(200);
        separator2.setPrefHeight(SCREENHEIGHT);

        Label textPreset = new Label("Select Preset");
        textPreset.getStyleClass().add("title");
        textPreset.setLayoutX(20);
        textPreset.setLayoutY(/*390*/545);
        
        //IMAGES FOR THE PRESETS
        
        Image Sun= new Image("file:sun.png");
        Image Earth= new Image("file:earth.png");
        Image Moon= new Image("file:moon.png");
        Image Mars= new Image("file:mars.png");
        Image Venus= new Image("file:venus.png");
        
        //PRESET #1
        ImageView SunView= new ImageView(Sun);
        SunView.setFitHeight(40);
        SunView.setFitWidth(40);

        ImageView EarthView= new ImageView(Earth);
        EarthView.setFitHeight(45);
        EarthView.setFitWidth(45);
        
        //PRESET #2
        ImageView SunView2= new ImageView(Sun);
        SunView2.setFitHeight(40);
        SunView2.setFitWidth(40);
        
        ImageView EarthView2= new ImageView(Earth);
        EarthView2.setFitHeight(45);
        EarthView2.setFitWidth(45); 
        
        ImageView MoonView= new ImageView(Moon);
        MoonView.setFitHeight(40);
        MoonView.setFitWidth(40);
        
       //PRESET #3
        ImageView SunView3= new ImageView(Sun);
        SunView3.setFitHeight(40);
        SunView3.setFitWidth(40);
        
        ImageView EarthView3= new ImageView(Earth);
        EarthView3.setFitHeight(45);
        EarthView3.setFitWidth(45); 
        
        ImageView MarsView= new ImageView(Mars);
        MarsView.setFitHeight(48);
        MarsView.setFitWidth(48);
        
        //PRESET #4
        ImageView SunView4= new ImageView(Sun);
        SunView4.setFitHeight(40);
        SunView4.setFitWidth(40);
        
        ImageView EarthView4= new ImageView(Earth);
        EarthView4.setFitHeight(45);
        EarthView4.setFitWidth(45); 
        
        ImageView VenusView= new ImageView(Venus);
        VenusView.setFitHeight(45);
        VenusView.setFitWidth(45);
        

        Rectangle preset1 = new Rectangle(180, 70);
        preset1.setFill(Color.TRANSPARENT);
        preset1.setStroke(Color.BLACK);
        preset1.setStrokeWidth(0.7);
        preset1.setLayoutX(10);
        preset1.setLayoutY(/*420*/600);
        
        HBox imagePreset1= new HBox(10,SunView,EarthView);
        imagePreset1.setAlignment(Pos.CENTER_LEFT);
        imagePreset1.setPadding(new Insets(10));
        imagePreset1.setMouseTransparent(true);
        
        StackPane finalPreset1= new StackPane(preset1,imagePreset1);
        finalPreset1.setLayoutX(10);
        finalPreset1.setLayoutY(/*420*/600);
        
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
        preset2.setLayoutY(/*510*/690);
        
        HBox imagePreset2= new HBox(10,SunView2,EarthView2,MoonView);
        imagePreset2.setAlignment(Pos.CENTER_LEFT);
        imagePreset2.setPadding(new Insets(10));
        imagePreset2.setMouseTransparent(true);
        
        StackPane finalPreset2= new StackPane(preset2,imagePreset2);
        finalPreset2.setLayoutX(10);
        finalPreset2.setLayoutY(/*510*/690);
        
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
        preset3.setLayoutY(/*600*/780);
        
        HBox imagePreset3= new HBox(10,SunView3,EarthView3,MarsView);
        imagePreset3.setAlignment(Pos.CENTER_LEFT);
        imagePreset3.setPadding(new Insets(10));
        imagePreset3.setMouseTransparent(true);
        
        StackPane finalPreset3= new StackPane(preset3,imagePreset3);
        finalPreset3.setLayoutX(10);
        finalPreset3.setLayoutY(/*600*/780);
        
        preset3.setOnMouseEntered(event -> {
            preset3.getScene().setCursor(Cursor.HAND); // Set cursor to hand
        });
        preset3.setOnMouseExited(event -> {
            preset3.getScene().setCursor(Cursor.DEFAULT); // Return cursor to default
        });
        preset3.setOnMouseClicked(e-> {
            // Add functionality here
        });
        
        Rectangle preset4 = new Rectangle(180, 70);
        preset4.setFill(Color.TRANSPARENT);
        preset4.setStroke(Color.BLACK);
        preset4.setStrokeWidth(0.7);
        preset4.setLayoutX(10);
        preset4.setLayoutY(/*600*/870);
        
        HBox imagePreset4= new HBox(10,SunView4,EarthView4,VenusView);
        imagePreset4.setAlignment(Pos.CENTER_LEFT);
        imagePreset4.setPadding(new Insets(10));
        imagePreset4.setMouseTransparent(true);
        
        StackPane finalPreset4= new StackPane(preset4,imagePreset4);
        finalPreset4.setLayoutX(10);
        finalPreset4.setLayoutY(/*600*/870);
        
        preset4.setOnMouseEntered(event -> {
            preset4.getScene().setCursor(Cursor.HAND); // Set cursor to hand
        });
        preset4.setOnMouseExited(event -> {
            preset4.getScene().setCursor(Cursor.DEFAULT); // Return cursor to default
        });
        preset4.setOnMouseClicked(e-> {
            // Add functionality here
        });

 
        Pane leftContainer = new Pane();
        leftContainer.getChildren().addAll(topLeftGrid, separator1, separator2,
                textPreset,finalPreset1, finalPreset2, finalPreset3,finalPreset4);
        root.setLeft(leftContainer);

        // Right side

        Slider massMultiplier = new Slider(0.5, 3.5, 1);
        Slider radiusMultiplier = new Slider(0.5, 3.5, 1);
        massMultiplier.setShowTickLabels(true);
        massMultiplier.setMajorTickUnit(0.5);
        massMultiplier.setMinorTickCount(0);
        massMultiplier.setShowTickMarks(true);
        massMultiplier.setSnapToTicks(true);
        radiusMultiplier.setShowTickLabels(true);
        radiusMultiplier.setShowTickMarks(true);
        radiusMultiplier.setMajorTickUnit(0.5);
        radiusMultiplier.setMinorTickCount(0);
        radiusMultiplier.setSnapToTicks(true);

        Label planetName = new Label();
        planetName.setText("Planet's Parameters");
        planetName.getStyleClass().add("title");
        Label velocity = new Label();
        velocity.setText("Velocity: 0 m/s");
        
        Label sliderName=new Label();
        sliderName.setText("Mass Multiplier");
        Label sliderName2= new Label();
        sliderName2.setText("Radius Multiplier");
        
        VBox MassMultiplier= new VBox(10,sliderName,massMultiplier);
        
        VBox RadiusMultiplier= new VBox(10,sliderName2,radiusMultiplier);
        
        VBox topRight = new VBox();
        topRight.setLayoutX(0);  //was 500
        topRight.setLayoutY(55);
        topRight.setSpacing(/*35*/50);
        topRight.setPadding(new Insets(25));
        topRight.getChildren().addAll(planetName, velocity,MassMultiplier,RadiusMultiplier);
        

        Separator separator3 = new Separator();
        separator3.setScaleX(33);
        separator3.setScaleY(3);
        separator3.setLayoutX(165);
        separator3.setLayoutY(/*370*/510);

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
  
        Slider scale = new Slider(0.5, 3.5, 1);
        scale.setShowTickLabels(true);
        scale.setMajorTickUnit(0.5);
        scale.setMinorTickCount(0);
        scale.setShowTickMarks(true);
        
        scale.valueProperty().addListener((obs,oldval,newval)->{
            System.out.println(newval.doubleValue());
        });
        
        Slider time = new Slider();
        Label scaleLabel = new Label("Scale");
        Label timeLabel = new Label("Time");
        
        VBox bottomRight = new VBox();
        bottomRight.setSpacing(/*17*/25);
        bottomRight.setLayoutX(20);
        bottomRight.setLayoutY(/*390*/550);
        bottomRight.getChildren().addAll(showPath, showGVectors, showVVectors,
                showGrid,scaleLabel, scale, timeLabel, time );

        Button start = new Button("Start");
        start.setOnAction(e->{
            
        });
        Button pause = new Button("Stop");
        pause.setOnAction(e->{
            
        });
        Button reset = new Button("Reset");
        reset.setOnAction(e->{
            
        });
        
        start.setLayoutX(20);
        start.setLayoutY(/*670*/920);
        pause.setLayoutX(70);
        pause.setLayoutY(/*670*/920);
        reset.setLayoutX(120);
        reset.setLayoutY(/*670*/920);

        Pane rightContainer = new Pane();
        rightContainer.getChildren().addAll(topRight, separator3, separator4,
                bottomRight, start, pause, reset);
        root.setRight(rightContainer);
        

        // Outer space (center)
        Canvas canvas = new Canvas(1100, 990);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        /*canvas.setOnMouseClicked(event -> {
            if ()
        });*/

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
        scene.getStylesheets().add("style.css");
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

    public void addPlanet(GridPane gridPane, ToggleGroup togglegroup, int row, int column) {
        Stage planetStage = new Stage();
        Button done = new Button("Done");
        MenuButton planetType = new MenuButton("Select Planet Type");

        String[] planetNames = {"Sun", "Earth", "Moon", "Mars", "Venus", "Neptune"};

        for (String planetName : planetNames) {
            MenuItem item = new MenuItem(planetName);
            item.setOnAction(e -> {
                selectedPlanetType = planetName;
                planetType.setText(selectedPlanetType);
            });
            planetType.getItems().add(item);
        }

        done.setOnAction(e -> {
            planetStage.close();
            ToggleButton toggleButton = new ToggleButton(selectedPlanetType);
            toggleButton.setToggleGroup(togglegroup);
            toggleButton.setPrefSize(160, 10);
            gridPane.add(toggleButton, row, column);
        });

        planetType.setMinSize(160, 10);
        VBox vbox = new VBox(planetType, done);
        Scene scene = new Scene(vbox);
        planetStage.setScene(scene);
        planetStage.show();
    }
}
