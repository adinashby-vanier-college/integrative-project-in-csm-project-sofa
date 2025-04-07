/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gravityandorbits;

import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Angela
 */
public class HelpInterface {
    public static Label planets=new Label("Customizing Planets");
    public static Label display= new Label("Display Options");
    public static Label simulationControls=new Label("Simulation Controls");
    public static Label fileManager= new Label("Project Management");
    
    public static void LoadHelpInterface(){
        
    planets.getStyleClass().add("sub-body");
    display.getStyleClass().add("sub-body");
    simulationControls.getStyleClass().add("sub-body");
    fileManager.getStyleClass().add("sub-body");
        

      String intro="This application will help you understand the theory of gravitational force between planets. You will be able to see how the force is affected by different factors with vectors and path like down below.";
      
      String features="FEATURES";
      
      String planetsDescription="Add Planets (up to 5 planets): \nClick on 'Add Custom Planet' or select from presets options \nChange Parameters: \nInteract with the slider 'Mass Multiplier/Radius Multiplier' on the right side ";
      
      String displayDescription= """
                                 Show Grid:
                                 Enable the checkbox 'Show Grid'.
                                 Show Path:
                                 Enable the checkbox 'Show Path'.
                                 Show Vectors:
                                 Enable the corresponding checkbox 'Show Gravity Vectors/Show Velocity Vectors'.""";
      
      String simulationDescription="""
                                   Change Scale:
                                   Interact with the slider 'Scale'.
                                   Change Time:
                                   Interact with the slider 'Time'.
                                   Start/Pause Animation:
                                   Click on the corresponding buttons 'Start/Pause'.
                                   Reset Animation:
                                   Click on the button 'Reset'. """;
      
      String fileDescription="""
                             Save Project:
                             Click on File and then click on save.
                             Load Project:
                             Click on File and then click on load
                             Choose the file from your File Explorer you want to open
                             Delete Project:
                             Click on File and then click on delete.
                             Choose the file from your File Explorer you want to delete.""";
     
      Label text1= new Label(intro);
      text1.setWrapText(true);
      text1.setLineSpacing(10);
      
      Label featuresText= new Label(features);
      featuresText.getStyleClass().add("title");
      
      Label text2= new Label(planetsDescription);
      text2.setWrapText(true);
      text2.setLineSpacing(10);
      
      Label text3= new Label(displayDescription);
      text3.setWrapText(true);
      text3.setLineSpacing(10);
      
      Label text4= new Label(simulationDescription);
      text4.setWrapText(true);
      text4.setLineSpacing(10);
      
      Label text5= new Label(fileDescription);
      text5.setWrapText(true);
      text5.setLineSpacing(10);
      
      Image Diagram=new Image("helpcenter.png");
      ImageView DiagramView= new ImageView(Diagram);
      DiagramView.setFitWidth(200);
      DiagramView.setFitHeight(200);
      DiagramView.setPreserveRatio(true);
      
      VBox intructions= new VBox(10,planets,text2,display,text3,simulationControls,text4,fileManager,text5);
      intructions.setAlignment(Pos.CENTER_LEFT);
      
      VBox content= new VBox(10,text1,DiagramView,featuresText,intructions);
      content.setAlignment(Pos.CENTER);
      content.getStyleClass().add("help");
          
      ScrollPane SP= new ScrollPane();
      SP.setContent(content);
      SP.setFitToWidth(true);
      SP.setPadding(new Insets(10));
      SP.getStyleClass().add("border");
      
      Stage stage= new Stage();
      stage.setTitle("Help");
      
      Scene scene= new Scene(SP,600,600);  
      scene.getStylesheets().add("style.css");
      stage.setScene(scene);
      stage.show();
      
      Settings settings= new Settings();
      settings.registerScene(scene);
    }
    
}
