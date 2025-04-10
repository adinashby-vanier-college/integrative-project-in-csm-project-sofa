package com.example.gravityandorbits;

import javafx.animation.AnimationTimer;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UI extends Parent {

    public final double SCREENWIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final double SCREENHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private Canvas canvas = new Canvas(1500, 990);
    private static final double G = 6.6743e-11;
    private List<Planet> planets = new ArrayList<>();


    private Map<ToggleButton, Planet> planetObjectMap = new HashMap<>();
    private Map<ToggleButton, Pane> planetPaneMap = new HashMap<>();
    private StackPane parameterDisplayPane = new StackPane();
    private GraphicsContext gc = canvas.getGraphicsContext2D();
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
        MenuItem Appearance= new MenuItem("Theme");
        Appearance.setOnAction(e->{
            Settings.theme();
        });

        MenuItem Language= new MenuItem("Language Preference");
        Language.setOnAction(e->{
          Settings.language();
        });
        
        Menu helpMenu = new Menu("Help");
        MenuItem help = new MenuItem("Help");
        help.setOnAction(e -> {
            HelpInterface.LoadHelpInterface();
        });

        Exit.getItems().addAll(exit);
        helpMenu.getItems().addAll(help);
        settings.getItems().addAll(Appearance,Language);
     
        menuBar.getMenus().addAll(Exit, file, settings, helpMenu);
        root.setTop(menuBar);

        // Left side
        GridPane topLeftGrid = new GridPane();
        topLeftGrid.setAlignment(Pos.TOP_LEFT);
        topLeftGrid.setGridLinesVisible(false);
        topLeftGrid.setHgap(20);
        topLeftGrid.setVgap(/*20*/45);

        ToggleGroup toggleGroup = new ToggleGroup();

        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            parameterDisplayPane.getChildren().clear();
            if (newToggle != null) {
                ToggleButton selectedButton = (ToggleButton) newToggle;
                Pane selectedPane = planetPaneMap.get(selectedButton);
                if (selectedPane != null) {
                    parameterDisplayPane.getChildren().add(selectedPane);
                }
            }
        });

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
        Settings button1=new Settings();
        button1.registerText(addCustomPlanet);
        
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
        textPreset.setLayoutX(15);
        textPreset.setLayoutY(/*390*/545);
        
        Settings preset= new Settings();
        preset.registerText(textPreset);
        
    
        //IMAGES FOR THE PRESETS
        
        Image Sun= new Image("sun.png");
        Image Earth= new Image("earth.png");
        Image Moon= new Image("moon.png");
        Image Mars= new Image("mars.png");
        Image Venus= new Image("venus.png");
        
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
        
        Settings check1= new Settings();
        check1.registerText(showPath);
        check1.registerText(showGrid);
        check1.registerText(showGVectors);
        check1.registerText(showVVectors);
  
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
        
        Settings sliders=new Settings();
        sliders.registerText(scaleLabel);
        sliders.registerText(timeLabel);
        
        VBox bottomRight = new VBox();
        bottomRight.setSpacing(/*17*/25);
        bottomRight.setLayoutX(20);
        bottomRight.setLayoutY(/*390*/550);
        bottomRight.getChildren().addAll(showPath, showGVectors, showVVectors,
                showGrid,scaleLabel, scale, timeLabel, time);

        Button start = new Button("Start");
        start.setOnAction(e->{
            
        });
        Button pause = new Button("Stop");
        pause.setOnAction(e->{
            
        });
        Button reset = new Button("Reset");
        reset.setOnAction(e->{
            
        });
        
        Settings buttons= new Settings();
        buttons.registerText(start);
        buttons.registerText(pause);
        buttons.registerText(reset);
        
        start.setLayoutX(20);
        start.setLayoutY(/*670*/920);
        pause.setLayoutX(70);
        pause.setLayoutY(/*670*/920);
        reset.setLayoutX(120);
        reset.setLayoutY(/*670*/920);

        Pane rightContainer = new Pane();
        rightContainer.getChildren().addAll(/*topRight*/parameterDisplayPane, separator3, separator4,
                bottomRight, start, pause, reset);
        root.setRight(rightContainer);
        

        // Outer space (center)


        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        canvas.setOnMouseClicked(event -> {
            Toggle activeButton = toggleGroup.getSelectedToggle();

            if (!(activeButton instanceof ToggleButton selectedButton)) return;

            double x = event.getX();
            double y = event.getY();

            Pane selectedPlanetPane = planetPaneMap.get(activeButton);
            //if (!(selectedPlanetPane instanceof Pane pane)) return;

            try {
                double mass = Double.parseDouble(((TextField) selectedPlanetPane.lookup("#massField")).getText());
                double velocity = Double.parseDouble(((TextField) selectedPlanetPane.lookup("#velocityField")).getText());
                double radius = Double.parseDouble(((TextField) selectedPlanetPane.lookup("#radiusField")).getText());

                Planet existingPlanet = planetObjectMap.get(selectedButton);

                if (existingPlanet == null) {
                    Planet newPlanet = new Planet(x, y, mass, velocity, radius);
                    planets.add(newPlanet);
                    planetObjectMap.put(selectedButton, newPlanet);
                } else {
                    existingPlanet.setX(x);
                    existingPlanet.setY(y);
                }

                spawnPlanet(gc);

            } catch (NumberFormatException e) {
                System.out.println("Invalid input in parameters.");
            }
        });

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

    public void addPlanet(GridPane gridPane, ToggleGroup togglegroup, int row, int column) {
       Stage planetStage = new Stage();
        Button done = new Button("Done");
        MenuButton planetType = new MenuButton("Select Planet Type");

        TextField chooseMassTextField = new TextField("Enter Mass");
        TextField chooseVelocityTextField = new TextField("Enter velocity");
        TextField chooseRadiusTextField = new TextField("Enter radius");

        String[] planetNames = {"Sun", "Earth", "Moon", "Mars", "Venus", "Neptune"};

        for (String planetName : planetNames) {
            MenuItem item = new MenuItem(planetName);
            item.setOnAction(e -> {
                selectedPlanetType = planetName;
                planetType.setText(selectedPlanetType);
            });
            planetType.getItems().add(item);
        }
        Label validChoice = new Label();
          
        done.setOnAction(e -> {

            if (selectedPlanetType == null) {
                validChoice.setText("You must chose a planet type");
            } else {
                planetStage.close();
                ToggleButton toggleButton = new ToggleButton(selectedPlanetType);
                toggleButton.setToggleGroup(togglegroup);
                toggleButton.setPrefSize(160, 10);
                gridPane.add(toggleButton, row, column); 
                
                VBox parameterPane = new VBox();
                parameterPane.setPadding(new Insets(10));
                parameterPane.getChildren().add(new Label(selectedPlanetType + " parameters"));


                Label velocityLabel = new Label("Initial Velocity: ");
                Label metersPerSecond = new Label("m/s");
                Label massLabel = new Label("Mass: ");
                Label kg = new Label("kg");
                Label radiusLabel = new Label("Radius Multiplier: ");
                Label m = new Label("m");

                TextField velocityTextField = new TextField();
                TextField massTextField = new TextField();
                TextField radiusTextField = new TextField();

                velocityTextField.setPrefSize(100, 10);
                massTextField.setPrefSize(100, 10);
                radiusTextField.setPrefSize(100, 10);

                massTextField.setText(chooseMassTextField.getText());
                massTextField.setId("massField");
                velocityTextField.setText(chooseVelocityTextField.getText());
                velocityTextField.setId("velocityField");
                radiusTextField.setText(chooseRadiusTextField.getText());
                radiusTextField.setId("radiusField");

                HBox velocityHBox = new HBox(10, velocityTextField, metersPerSecond);
                HBox massHBox = new HBox(10, massTextField, kg);
                HBox radiusHBox = new HBox(10, radiusTextField, m);
              
                VBox velocityVBox = new VBox(velocityLabel, velocityHBox);
                VBox massVBox = new VBox(massLabel, massHBox);
                VBox radiusVBox = new VBox(radiusLabel, radiusHBox);  
                              
                parameterPane.setLayoutX(0);  //was 500
                parameterPane.setLayoutY(55);
                parameterPane.setSpacing(/*35*/50);
                parameterPane.setPadding(new Insets(25));
                parameterPane.getChildren().addAll(velocityVBox, massVBox, radiusVBox);
                planetPaneMap.put(toggleButton, parameterPane);
            }
        });
                
        planetType.setMinSize(160, 10);
        VBox vbox = new VBox(10, planetType, chooseMassTextField, chooseVelocityTextField, chooseRadiusTextField,validChoice, done);
        vbox.setPadding(new Insets(10));
        Scene scene = new Scene(vbox);
        scene.getStylesheets().add("style.css");
        planetStage.setScene(scene);
        planetStage.show();
    }

    public void spawnPlanet(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Planet planet : planets) {
            gc.setFill(planet.color);
            gc.fillOval(planet.x - 10, planet.y - 10, planet.radius, planet.radius);
        }
    }

    public void animatePlanets(List<Planet> planets) {
        for (Planet p1 : planets) {
            double ax = 0;
            double ay = 0;

            for (Planet p2 : planets) {
                if (p1 == p2) {
                    continue;
                }

                double dx = p2.x - p1.x;
                double dy = p2.y - p1.y;
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < 5) {
                    distance = 5;
                }

                double force = G * p1.mass * p2.mass / (distance * distance);
                double fx = force * dx / distance;
                double fy = force * dy / distance;

                ax += fx / p1.mass;
                ay += fy / p1.mass;
            }

            p1.velocityX += ax;
            p1.velocityY += ay;
        }
        for (Planet p : planets) {
            p.x += p.velocityX;
            p.y += p.velocityY;
        }
    }

    long lastUpdate = System.nanoTime();

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            double dt = (l - lastUpdate) / 1e9;
            lastUpdate = l;
            animatePlanets(planets);
            spawnPlanet(gc);
        }
    };

    public void startTimer() {
        timer.start();
    }

}
