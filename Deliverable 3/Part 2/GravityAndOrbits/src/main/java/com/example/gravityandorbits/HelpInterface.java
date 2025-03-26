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
    public static void LoadHelpInterface(){
        
      Stage stage= new Stage();
      stage.setTitle("Help");
     
      String intro="This application will help you understand the theory of gravitational force between planets. You will be able to see how the force is affected by different factors with vectors and path like down below.";
      String features="FEATURES";
      
      String instructions2= """
                            Add Planets: Click on 'Add Custom Planet' up to 5 planets / or Select from the presets.
                            Change Parameters: Interact with the slider 'Mass Multiplier/Radius Multiplier' on the right side.
                            Show Grid: Click the checkbox 'Show Grid'.
                            Show Path: Click the checkbox 'Show Path'.
                            Show Vectors: Click the corresponding checkbox 'Show Gravity Vectors/Show Velocity Vectors'.
                            Change Scale: Interact with the slider 'Scale'.
                            Change Time: Interact with the slider 'Time'.
                            Start/Pause Animation: Click on the corresponding buttons 'Start/Pause'.
                            Reset Animation: Click on the button 'Reset'. 
                            Save Project: Click on File and then click on save.""";

      Label text1= new Label(intro);
      text1.setWrapText(true);
      text1.setLineSpacing(10);
      
      Label featuresText= new Label(features);
      featuresText.getStyleClass().add("title");
      
      Label text2= new Label(instructions2);
      text2.setWrapText(true);
      text2.setLineSpacing(10);
      
      Image Diagram=new Image("file:helpcenter.png");
      ImageView DiagramView= new ImageView(Diagram);
      DiagramView.setFitWidth(200);
      DiagramView.setFitHeight(200);
      DiagramView.setPreserveRatio(true);
      
      VBox content= new VBox(10,text1,DiagramView,featuresText,text2);
      content.setAlignment(Pos.CENTER);
      content.getStyleClass().add("help");
          
      ScrollPane SP= new ScrollPane();
      SP.setContent(content);
      SP.setFitToWidth(true);
      SP.setPadding(new Insets(10));
      SP.getStyleClass().add("border");

      Scene scene= new Scene(SP,600,600);  
      scene.getStylesheets().add("style.css");
      stage.setScene(scene);
      stage.show();
        
    }
    
}
