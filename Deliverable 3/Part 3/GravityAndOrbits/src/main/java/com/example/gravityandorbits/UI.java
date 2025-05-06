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
    private StackPane parameterDisplayPane = new StackPane();
    public GraphicsContext gc = canvas.getGraphicsContext2D();
    public String selectedPlanetType = null;
    public Planet selectedPlanet = null;
    private boolean awaitingVelocityClick = false;
    private int rowCount = 0;
    private final Text warningMsg = new Text("Cannot add more than 5 planets.");
    private boolean showGridState = false;
    private double timeScale = 1.0;
    private long last = 0;
    private boolean velocitySetMode = false;
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

        planets.add(new Planet("Sun", 400, 300, 333000, 30, 0, 0));

        // Test Planet #2: like an “earth” orbiting to the right
        planets.add(new Planet("Earth", 500, 300, 1, 10, 0, 57.72));


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
        button1.registerLabel(addCustomPlanet);
        
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
        CheckBox showGVectors = new CheckBox("Show Accel Vectors");
        CheckBox showVVectors = new CheckBox("Show Velocity Vectors");
        CheckBox showGrid = new CheckBox("Show Grid");
        
        Settings check1 = new Settings();
        check1.registerLabel(showPath);
        check1.registerLabel(showGrid);
        check1.registerLabel(showGVectors);
        check1.registerLabel(showVVectors);
  
        Slider scale = new Slider(0.5, 3.5, 1);
        scale.setShowTickLabels(true);
        scale.setMajorTickUnit(0.5);
        scale.setMinorTickCount(0);
        scale.setShowTickMarks(true);
        
        scale.valueProperty().addListener((obs,oldval,newval)->{
            System.out.println(newval.doubleValue());
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
                showGrid, timeLabel, time);

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
          topLeftGrid.getChildren().removeAll(toggleGroup.getToggles());
          rowCount=0;
          planets.clear();
          gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
          gc.setFill(Color.BLACK);
          gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
          
          Toggle selectedToggle=toggleGroup.getSelectedToggle();
          if(selectedToggle instanceof ToggleButton toggleButton){
              Pane parameterPane=planetPaneMap.get(toggleButton);
              if(parameterPane!=null&&parameterPane.getParent() instanceof Pane parentPane){
                  parentPane.getChildren().remove(parameterPane);
              }
          }
            
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
        root.setRight(rightContainer);
        

        // Outer space (center)


        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        canvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            if (awaitingVelocityClick && selectedPlanet != null) {
                // 3rd click: apply velocity
                Vector pos = selectedPlanet.getPosition();
                double dx = x - pos.getX();
                double dy = y - pos.getY();
                selectedPlanet.setVelFromDrag(dx, dy);
                System.out.println("Velocity set: " + selectedPlanet.getVelocity());
                awaitingVelocityClick = false;
                return;
            }

            // Check if click is on a planet
            for (Planet p : planets) {
                double dx = x - p.getPosition().getX();
                double dy = y - p.getPosition().getY();
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance <= p.getRadius()) {
                    if (event.getClickCount() == 2 && p == selectedPlanet) {
                        // Double-click on already selected planet: enter velocity mode
                        awaitingVelocityClick = true;
                        System.out.println("Double-click: Enter velocity set mode for " + p.getName());
                    } else {
                        // Single-click: select planet
                        selectedPlanet = p;
                        awaitingVelocityClick = false;
                        System.out.println("Selected planet: " + p.getName());
                    }
                    return;
                }
            }

            // Clicked empty space — reset
            awaitingVelocityClick = false;
            Toggle activeButton = toggleGroup.getSelectedToggle();
            if (!(activeButton instanceof ToggleButton selectedButton)) return;
            Pane selectedPlanetPane = planetPaneMap.get(activeButton);
            //if (!(selectedPlanetPane instanceof Pane pane)) return;

            try {
                double mass = Double.parseDouble(((TextField) selectedPlanetPane.lookup("#massField")).getText());
                double velocity = Double.parseDouble(((TextField) selectedPlanetPane.lookup("#velocityField")).getText());
                double radius = Double.parseDouble(((TextField) selectedPlanetPane.lookup("#radiusField")).getText());

                Planet existingPlanet = planetObjectMap.get(selectedButton);

                if (existingPlanet == null) {
                    Planet newPlanet = new Planet("Planet", 700, 300, 500, 30,300,300);//added "10" as dummy; remove later
                    planets.add(newPlanet);
                    planetObjectMap.put(selectedButton, newPlanet);
                } else {
                    //existingPlanet.setX(x);
                    //existingPlanet.setY(y);
                }


            } catch (NumberFormatException e) {
                System.out.println("Invalid input in parameters.");
            }
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
            }
            else {
                renderer.setDrawAccelerationVectors(false);
            }
        });

        showVVectors.setOnAction(e->{
            if (showVVectors.isSelected()) {
                renderer.setDrawVelocityVectors(true);
            }
            else {
                renderer.setDrawVelocityVectors(false);
            }
        });


        //To show grid
        showGrid.setOnAction(e->{
            if (showGrid.isSelected()) {
                setShowGrid(true);
            }
            else {
                setShowGrid(false);
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

    public void addPlanet(GridPane gridPane, ToggleGroup togglegroup, int row, int column) {
        Stage planetStage = new Stage();
        Button done = new Button("Done");
        MenuButton planetType = new MenuButton("Select Planet Type");
        
        TextField chooseMassTextField = new TextField();
        chooseMassTextField.setPromptText("Enter Mass...");
        TextField chooseVelocityTextField = new TextField();
        chooseVelocityTextField.setPromptText("Enter velocity...");
        TextField chooseRadiusTextField = new TextField();
        chooseRadiusTextField.setPromptText("Enter radius...");
        

        String[] planetNames = {"Sun", "Earth", "Moon", "Mars", "Venus", "Neptune"};

        for (String planetName : planetNames) {
            MenuItem item = new MenuItem(planetName);
            
            item.setOnAction(e -> {
                selectedPlanetType = planetName;
                planetType.setText(selectedPlanetType);         
                Settings pt= new Settings();
                pt.registerLabel(planetType);
            });
            planetType.getItems().add(item);
            Settings planetSelection= new Settings();
            planetSelection.registerMenuItems(item);
        }
        Label validChoice = new Label();
        
        Settings addplanet = new Settings();
        addplanet.registerLabel(planetType);
        addplanet.registerLabel(done);
        addplanet.registerTextField(chooseMassTextField);
        addplanet.registerTextField(chooseVelocityTextField);
        addplanet.registerTextField(chooseRadiusTextField);
          
        done.setOnAction(e -> {

            if (selectedPlanetType == null) {
                validChoice.setText("You must choose a planet type");
                addplanet.registerLabel(validChoice);
            } else {
                planetStage.close();
                ToggleButton toggleButton = new ToggleButton(selectedPlanetType);
                toggleButton.setToggleGroup(togglegroup);
                toggleButton.setPrefSize(160, 10);
                gridPane.add(toggleButton, row, column); 

                VBox parameterPane = new VBox();
                parameterPane.setPadding(new Insets(10));
                parameterPane.getChildren().add(new Label(selectedPlanetType + " parameters"));
           
                Settings planets= new Settings();
                planets.registerLabel(toggleButton);


                Label velocityLabel = new Label("Initial Velocity: ");
                Label metersPerSecond = new Label("m/s");
                Label massLabel = new Label("Mass: ");
                Label kg = new Label("kg");
                Label radiusLabel = new Label("Radius Multiplier: ");
                Label m = new Label("m");
                
                Settings planetParameters= new Settings();
                planetParameters.registerLabel((Label)parameterPane.getChildren().get(0));
                planetParameters.registerLabel(massLabel);
                planetParameters.registerLabel(velocityLabel);
                planetParameters.registerLabel(radiusLabel);
                planetParameters.registerLabel(planetType);
               

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
        VBox vbox = new VBox(10, planetType, chooseMassTextField, chooseVelocityTextField, chooseRadiusTextField, validChoice, done);
        vbox.setPadding(new Insets(10));
        Scene scene = new Scene(vbox);
        scene.getStylesheets().add("style.css");
        planetStage.setScene(scene);
        planetStage.show();
        
        addplanet.registerScene(scene);
    }

    /** Toggle grid on/off */
    public void setShowGrid(boolean show) {
        this.showGridState = show;
    }

    public void startAnimation() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (last == 0) { last = now; return; }
                double dt = (now - last) / 1e9;
                last = now;

                Calculation.updateAll(planets, dt*timeScale);
                renderer.renderFrame(gc, planets, showGridState,
                        canvas.getWidth(), canvas.getHeight(),selectedPlanet);
            }      
    };  
    timer.start();
    }

}
