package com.example.gravityandorbits;

import static com.example.gravityandorbits.Settings.applyLanguageToAllLabels;
import static com.example.gravityandorbits.Settings.applyLanguageToMenuBar;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
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
    public Canvas canvas = new Canvas(1500, 990);
    private List<Planet> planets = new ArrayList<>();
    Renderer renderer = new Renderer();


    private Map<ToggleButton, Planet> planetObjectMap = new HashMap<>();
    private Map<ToggleButton, Pane> planetPaneMap = new HashMap<>();
    private VBox parameterDisplayPane = new VBox();
    public GraphicsContext gc = canvas.getGraphicsContext2D();
    public Planet selectedPlanet = null;
    private boolean awaitingVelocityClick = false;
    private boolean isSettingPosition = false;
    private final Text warningMsg = new Text("Cannot add more than 5 planets.");
    private boolean showGridState = false;
    private boolean showOrbitPaths = false;
    private double timeScale = 1.0;
    private long last = 0;
    private VBox planetsButtonBox;
    private Timeline longPressTimer;
    AnimationTimer timer;
    
    public BorderPane initialize() {
        BorderPane root = new BorderPane();  
        // MenuBar
        MenuBar menuBar = new MenuBar();
        Menu Exit = new Menu("Exit");
        MenuItem exit = new MenuItem("Exit the application");
        exit.setOnAction(e -> {
            exitButton();
        });
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
     
        menuBar.getMenus().addAll(Exit, settings, helpMenu);
        root.setTop(menuBar);
        
        Settings Menu= new Settings();
        Menu.registerMenu(Exit);
        Menu.registerMenu(settings);
        Menu.registerMenu(helpMenu);

        Settings MenuItem= new Settings();
        MenuItem.registerMenuItems(exit);
        MenuItem.registerMenuItems(help);
        MenuItem.registerMenuItems(Appearance);
        MenuItem.registerMenuItems(Language);


        // Left side
        VBox topLeftBox = new VBox(15);
        topLeftBox.setAlignment(Pos.CENTER);

        planetsButtonBox = new VBox(15);
        planetsButtonBox.setAlignment(Pos.CENTER);
        planetsButtonBox.setSpacing(15);
        planetsButtonBox.setPrefHeight(400);
        topLeftBox.getChildren().add(planetsButtonBox);

        // Add the Button to the VBox
        Button addCustomPlanet = new Button("Add Custom Planet");
        topLeftBox.getChildren().add(addCustomPlanet);
        topLeftBox.setPadding(new Insets(30));


        addCustomPlanet.setOnAction(e -> {
            parameterDisplayPane.getChildren().clear();
            setupCustomPlanet(parameterDisplayPane);
        });


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
        preset.registerLabel(textPreset);
        
    
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
            // Preset functionality
            planets = new ArrayList<>();
            planets.add(new Planet("Sun", 750, 495, 333000, 100, 0, 0));
            planets.add(new Planet("Earth", 1000, 495, 1, 40, 0, 115.45));

            isSettingPosition = false;awaitingVelocityClick = false;

            selectedPlanet = null;
            renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
            loadPlanetButtons(planets, planetsButtonBox);
            loadPlanetSettings(selectedPlanet, parameterDisplayPane);
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
            // Preset functionality
            planets = new ArrayList<>();
            // Place Sun at center
            planets.add(new Planet("Sun",   750, 495, 333000, 100, 0, 0));
            planets.add(new Planet("Earth", 1000, 495, 1, 40, 0, 115.4));
            planets.add(new Planet("Moon",  970, 495, 0.01, 10, 0, 116.0));

            isSettingPosition = false;awaitingVelocityClick = false;

            selectedPlanet = null;
            renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
            loadPlanetButtons(planets, planetsButtonBox);
            loadPlanetSettings(selectedPlanet, parameterDisplayPane);
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
            // Preset functionality
            planets=new ArrayList<>();
            planets.add(new Planet("Sun",   750, 495, 333000, 100, 0, 0));
            planets.add(new Planet("Earth", 1000, 495, 1, 40, 0, 115.4));
            planets.add(new Planet("Mars", 1150, 495, 0.05, 20, 0, 91.3));

            isSettingPosition = false;awaitingVelocityClick = false;

            selectedPlanet = null;
            renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
            loadPlanetButtons(planets, planetsButtonBox);
            loadPlanetSettings(selectedPlanet, parameterDisplayPane);
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
            // Preset functionality
            planets=new ArrayList<>();
            planets.add(new Planet("Sun",   750, 495, 333000, 100, 0, 0));
            planets.add(new Planet("Venus", 950, 495, 0.82, 30, 0, 129.1));
            planets.add(new Planet("Earth", 1000, 495, 1,    40, 0, 115.4));

            isSettingPosition = false; awaitingVelocityClick = false;

            selectedPlanet = null;
            renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
            loadPlanetButtons(planets, planetsButtonBox);
            loadPlanetSettings(selectedPlanet, parameterDisplayPane);
        });

 
        Pane leftContainer = new Pane();
        leftContainer.getChildren().addAll(topLeftBox, separator1, separator2,
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
        CheckBox showGVectors = new CheckBox("Show Accel Vectors");
        CheckBox showVVectors = new CheckBox("Show Velocity Vectors");
        CheckBox showGrid = new CheckBox("Show Grid");
        
        Settings check1 = new Settings();
        check1.registerLabel(showPath);
        check1.registerLabel(showGrid);
        check1.registerLabel(showGVectors);
        check1.registerLabel(showVVectors);

        Label GLabel = new Label("Gravitational Constant");
        Slider GSlider = new Slider(3, 30, 9);
        GSlider.setShowTickLabels(true);
        GSlider.setMajorTickUnit(3);
        GSlider.setMinorTickCount(0);
        GSlider.setShowTickMarks(true);

        GSlider.valueProperty().addListener((obs,oldval,newval)->{
            Calculation.setG(newval.doubleValue());
        });

        Label timeLabel = new Label("Time");
        Slider time = new Slider(0.25, 5.0, 1.0);
        time.setBlockIncrement(0.5);
        time.setMajorTickUnit(0.5);
        time.setMinorTickCount(0);
        time.setSnapToTicks(true);
        time.setShowTickMarks(true);
        time.setShowTickLabels(true);

        time.valueProperty().addListener((obs, oldVal, newVal) -> {
            timeScale = newVal.doubleValue();
        });
        
        Settings sliders=new Settings();
        sliders.registerLabel(timeLabel);
        
        VBox bottomRight = new VBox();
        bottomRight.setSpacing(25);
        bottomRight.setLayoutX(20);
        bottomRight.setLayoutY(550);
        bottomRight.getChildren().addAll(showPath, showGVectors, showVVectors,
                showGrid, GLabel, GSlider, timeLabel, time);

        Button start = new Button("Start");
        start.setOnAction(e->{
            last = 0;
            timer.start();
        });
        Button pause = new Button("Stop");
        pause.setOnAction(e->{
            timer.stop();
        });
        Button reset = new Button("Reset");
        reset.setOnAction(e->{
          planets.clear();
          renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
          loadPlanetButtons(planets, planetsButtonBox);
        });
        
        Settings buttons= new Settings();
        buttons.registerLabel(start);
        buttons.registerLabel(pause);
        buttons.registerLabel(reset);
        
        start.setLayoutX(20);
        start.setLayoutY(/*670*/920);
        pause.setLayoutX(70);
        pause.setLayoutY(/*670*/920);
        reset.setLayoutX(120);
        reset.setLayoutY(/*670*/920);

        Pane rightContainer = new Pane();
        rightContainer.getChildren().addAll(/*topRight*/parameterDisplayPane, separator3, separator4,
                bottomRight, start, pause, reset);
        parameterDisplayPane.setAlignment(Pos.CENTER);
        parameterDisplayPane.setPadding(new Insets(40));
        parameterDisplayPane.setSpacing(20);
        root.setRight(rightContainer);
        

        // Outer space (center)


        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        canvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            if (awaitingVelocityClick && selectedPlanet != null) {
                // 3rd click: apply velocity
                loadPlanetSettings(selectedPlanet, parameterDisplayPane);
                Vector pos = selectedPlanet.getPosition();
                double dx = x - pos.getX();
                double dy = y - pos.getY();
                selectedPlanet.setVelFromDrag(dx, dy);
                renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
                System.out.println("Velocity set: " + selectedPlanet.getVelocity());
                awaitingVelocityClick = false;
                return;
            }
            else if (isSettingPosition && selectedPlanet != null) {
                selectedPlanet.setPosition(new Vector(x, y));  // Directly set x, y on existing Vec2
                isSettingPosition = false;
                System.out.printf("Moved to: %.1f, %.1f%n", x, y);
                return;
            }
            else {
                // Check if click is on a planet
                for (Planet p : planets) {
                    double dx = x - p.getPosition().getX();
                    double dy = y - p.getPosition().getY();
                    double distance = Math.sqrt(dx * dx + dy * dy);

                    if (distance <= p.getRadius()) {
                        if (event.getClickCount() == 2 && p == selectedPlanet) {
                            // Double-click on already selected planet: enter velocity mode
                            loadPlanetSettings(selectedPlanet, parameterDisplayPane);
                            awaitingVelocityClick = true;
                            System.out.println("Double-click: Enter velocity set mode for " + p.getName());
                        } else {
                            // Single-click: select planet
                            selectedPlanet = p;
                            loadPlanetSettings(selectedPlanet, parameterDisplayPane);
                            renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(), selectedPlanet);
                            awaitingVelocityClick = false;
                            System.out.println("Selected planet: " + p.getName());
                        }
                        return;
                    }
                }
            }

            // Clicked empty space — reset
            awaitingVelocityClick = false;
        });
        //to cancel hold action
        canvas.setOnMouseReleased(e -> {
            if (longPressTimer != null) {
                longPressTimer.stop();
                longPressTimer = null;
            }
        });

        //To show vectors
        showGVectors.setOnAction(e->{
            if (showGVectors.isSelected()) {
                renderer.setDrawAccelerationVectors(true);
                renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
            }
            else {
                renderer.setDrawAccelerationVectors(false);
                renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
            }
        });

        showVVectors.setOnAction(e->{
            if (showVVectors.isSelected()) {
                renderer.setDrawVelocityVectors(true);
                renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
            }
            else {
                renderer.setDrawVelocityVectors(false);
                renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
            }
        });

        //to show path
        showPath.setOnAction(e->{
            showOrbitPaths = showPath.isSelected();
            renderer.setDrawOrbits(showOrbitPaths);
        });

        //To show grid
        showGrid.setOnAction(e->{
            if (showGrid.isSelected()) {
                setShowGrid(true);
                renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
            }
            else {
                setShowGrid(false);
                renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
            }
        });

        root.setCenter(canvas);
        return root;
    }

    // Set the functionality for exit button
    public void exitButton() {
        Stage stage = new Stage();
        Pane pane = new Pane();
        Label text = new Label("Are you sure you want to exit the application?");
        Button yes = new Button("Yes");
        Button no = new Button("No");
        
        Settings exit= new Settings();
        exit.registerLabel(yes);
        exit.registerLabel(no);
        exit.registerLabel(text);
        
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
        
        Settings settings= new Settings();
        settings.registerScene(scene);
    }


    // Toggle grid on/off
    public void setShowGrid(boolean show) {
        this.showGridState = show;
    }

    //animation loop
    public void startAnimation() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (last == 0) { last = now; return; }
                double dt = (now - last) / 1e9;
                last = now;

                Calculation.updateAll(planets, dt*timeScale);

                renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);



            }      
    };  
    timer.start();
    }

    public void loadPlanetButtons(List<Planet> planets, VBox vbox) {
        vbox.getChildren().clear();
        for (Planet planet : planets) {
            Button button = new Button(planet.getName());
            vbox.getChildren().add(button);

            button.setOnAction(e -> {
                selectedPlanet = planet;
                loadPlanetSettings(selectedPlanet, parameterDisplayPane);
                renderer.renderFrame(gc, planets, showGridState, canvas.getWidth(), canvas.getHeight(),selectedPlanet);
            });
        }
    }

    public void loadPlanetSettings(Planet planet, VBox vbox) {
        vbox.getChildren().clear();
        if (planet == null) {
            vbox.getChildren().add(new Label("No planet selected!"));
            return; }
        vbox.getChildren().add(new Label("Name: " + planet.getName()));
        vbox.getChildren().add(new Label("Base Mass: " + String.format("%.2e", planet.getBaseMass() * 5.972e24) + " kg"));
        vbox.getChildren().add(new Label("Velocity: " + String.format("%.2f", planet.getVelocity().magnitude()) + " px/s"));
        vbox.getChildren().add(new Label("Mass Multiplier"));

        Slider massSlider = new Slider(0.5, 3, 1);
        massSlider.setMaxWidth(150);
        massSlider.setShowTickLabels(true);
        massSlider.setShowTickMarks(true);
        massSlider.setMajorTickUnit(0.5);
        massSlider.setMinorTickCount(0);
        massSlider.setSnapToTicks(true);
        vbox.getChildren().add(massSlider);

        massSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (selectedPlanet != null) {
                selectedPlanet.setMass(selectedPlanet.getBaseMass() * newVal.doubleValue());
            }
        });

        //  Set Position Button
        Button setPositionButton = new Button("Set Position");
        setPositionButton.setOnAction(e -> {
            isSettingPosition = true;
            System.out.println("Click anywhere to move " + planet.getName());
        });
        vbox.getChildren().add(setPositionButton);

        //set velocity button
        Button setVelocityButton = new Button("Set Velocity");
        setVelocityButton.setOnAction(e -> {
            awaitingVelocityClick = true;
        });
        vbox.getChildren().add(setVelocityButton);

        //delete planet button
        Button deleteButton = new Button("Delete Planet");
        deleteButton.setOnAction(e -> {
            planets.remove(planet);
        });
        vbox.getChildren().add(deleteButton);
    }

    public void setupCustomPlanet(VBox parameterDisplayPane) {
        // Create the ComboBox for planet selection
        parameterDisplayPane.setPadding(new Insets(30));
        ComboBox<Planet> planetComboBox = new ComboBox<>();
        planetComboBox.setPromptText("Select New Planet");
        planetComboBox.getItems().add(new Planet("Sun", 750, 496, 333000, 100, 0, 0));
        planetComboBox.getItems().add(new Planet("Earth", 1000, 495, 1, 40, 0, 115.4));
        planetComboBox.getItems().add(new Planet("Venus", 1100, 495, 0.815, 30, 0, 97.6));
        planetComboBox.getItems().add(new Planet("Mars", 1200, 495, 0.107, 20, 0, 85.9));

        // Create a VBox to hold the ComboBox and buttons
        VBox planetSettingsVBox = new VBox();
        planetSettingsVBox.getChildren().add(planetComboBox);

        // Update settings based on planet selection
        planetComboBox.setOnAction(e -> {
            Planet selectedPlanet = planetComboBox.getValue();
            planets.add(planetComboBox.getValue());
            loadPlanetSettings(selectedPlanet, parameterDisplayPane);
            loadPlanetButtons(planets, planetsButtonBox);
        });

        parameterDisplayPane.getChildren().clear();
        parameterDisplayPane.getChildren().add(planetSettingsVBox);

    }
}
