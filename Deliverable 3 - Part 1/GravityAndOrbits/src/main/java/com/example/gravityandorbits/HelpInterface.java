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
     
      String instructions1="Certainly elsewhere my do allowance at. The address farther six hearted hundred towards husband. Are securing off occasion remember daughter replying. Held that feel his see own yet. Strangers ye to he sometimes propriety in. She right plate seven has. Bed who perceive judgment did marianne.\n" +
"\n" +
"Her extensive perceived may any sincerity extremity. Indeed add rather may pretty see. Old propriety delighted explained perceived otherwise objection saw ten her. Doubt merit sir the right these alone keeps. By sometimes intention smallness he northward. Consisted we otherwise arranging commanded discovery it explained. Does cold even song like two yet been. Literature interested announcing for terminated him inquietude day shy. Himself he fertile chicken perhaps waiting if highest no it. Continued promotion has consulted fat improving not way.\n" +
"\n" +
"In to am attended desirous raptures declared diverted confined at. Collected instantly remaining up certainly to necessary as. Over walk dull into son boy door went new. At or happiness commanded daughters as. Is handsome an declared at received in extended vicinity subjects. Into miss on he over been late pain an. Only week bore boy what fat case left use. Match round scale now sex style far times. Your me past an much.";
      
      String instructions2="";
      Label text1= new Label(instructions1);
      text1.setWrapText(true);
      Label text2= new Label(instructions1);
      text2.setWrapText(true);
      
      Image Diagram=new Image("file:helpcenter.png");
      ImageView DiagramView= new ImageView(Diagram);
      DiagramView.setFitWidth(200);
      DiagramView.setFitHeight(200);
      DiagramView.setPreserveRatio(true);
      
      BorderPane content=new BorderPane();
      content.setTop(text1);
      content.setCenter(DiagramView);
      content.setBottom(text2);
      content.getStyleClass().add("root");
          
      ScrollPane SP= new ScrollPane();
      SP.setContent(content);
      SP.setFitToWidth(true);
      SP.setPadding(new Insets(10));
      SP.getStyleClass().add("border");

      Scene scene= new Scene(SP,500,500);  
      scene.getStylesheets().add("style.css");
      stage.setScene(scene);
      stage.show();
        
    }
    
}
