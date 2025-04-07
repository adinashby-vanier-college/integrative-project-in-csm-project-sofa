/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gravityandorbits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Angela
 */
public class Settings{
       
    private static List<Scene> registeredScenes= new ArrayList<>();
    private static List<Labeled> registeredLabel=new ArrayList<>();
    
    
    public void registerScene(Scene scene){
        registeredScenes.add(scene);
    }
     public void registerText(Labeled label){
         registeredLabel.add(label);
     }
   
    public static void theme(){
      Label text= new Label("Select Theme According to Your Preferences");  
      RadioButton dark= new RadioButton("Dark");  
      RadioButton light= new RadioButton("Light");
      ToggleGroup radioGroup= new ToggleGroup();
      dark.setToggleGroup(radioGroup);
      light.setToggleGroup(radioGroup);
      
      Settings appearancetext= new Settings();
      appearancetext.registerText(text);
      appearancetext.registerText(light);
      appearancetext.registerText(dark);
      
      light.setSelected(true);
      
      dark.setOnAction(e->{
        for(Scene Mainscene:registeredScenes){
        Mainscene.getStylesheets().clear();
        Mainscene.getStylesheets().add("darkmode.css");
        }  
      });
      
      light.setOnAction(e->{
       for(Scene Mainscene:registeredScenes){
        Mainscene.getStylesheets().clear();
        Mainscene.getStylesheets().add("style.css");
        }  
      });
      
      VBox appearance= new VBox(10,text,dark,light);
      appearance.setPadding(new Insets(10));
      
      Stage stage= new Stage();
      stage.setTitle("Theme"); 
      Scene scene= new Scene(appearance,300,300);  
      scene.getStylesheets().add("style.css");
      stage.setScene(scene);
      stage.show();
      
      Settings settings= new Settings();
      settings.registerScene(scene);
    }
    
    public static Map<String,String> eng_fr=new HashMap<>();
    public static Map<String,String> fr_eng=new HashMap<>();
    
    
    public static void language(){
      Label text= new Label("Select a Language");  
      RadioButton english= new RadioButton("English");
      RadioButton french= new RadioButton("French");
      ToggleGroup radioGroup= new ToggleGroup();
      english.setToggleGroup(radioGroup);
      french.setToggleGroup(radioGroup);
      
      Settings languages= new Settings();
      languages.registerText(text);
      languages.registerText(english);
      languages.registerText(french);
      
      //TRANSLATION ENGLISH TO FRENCH
      eng_fr.put("Select Preset","Sélectionner Préréglage");
      eng_fr.put("Time","Temps");
      eng_fr.put("Add Custom Planet", "Ajouter Planètes Perso.");
      eng_fr.put("Show Path", "Montrer la Trajectoire");
      eng_fr.put("Show Grid", "Montrer la Grille");
      eng_fr.put("Show Gravity Vectors", "Montrer les vecteurs de gravité");
      eng_fr.put("Show Velocity Vectors","Montrer les vecteurs de vitesse");
      eng_fr.put("Scale","Échelle");
      eng_fr.put("Start","Dém.");
      eng_fr.put("Stop","Pause");
      eng_fr.put("Reset","Réin.");
      eng_fr.put("Select Theme According to Your Preferences", "Sélectionner le thème selon vos préférences");
      eng_fr.put("Dark","Sombre");
      eng_fr.put("Light", "Clair");
      eng_fr.put("Select a Language", "Sélectionner un Language");
      eng_fr.put("English", "Anglais");
      eng_fr.put("French", "Français");
      
      //TRANSLATION FRENCH TO ENGLISH
      fr_eng.put("Sélectionner Préréglage","Select Preset");
      fr_eng.put("Temps","Time");
      fr_eng.put("Ajouter Planètes Perso.","Add Custom Planet");
      fr_eng.put("Montrer la Trajectoire","Show Path");
      fr_eng.put("Montrer la Grille","Show Grid");
      fr_eng.put("Montrer les vecteurs de gravité","Show Gravity Vectors");
      fr_eng.put("Montrer les vecteurs de vitesse","Show Velocity Vectors");
      fr_eng.put("Échelle","Scale");
      fr_eng.put("Dém.","Start");
      fr_eng.put("Pause","Stop");
      fr_eng.put("Réin.","Reset");
      fr_eng.put("Sélectionner le thème selon vos préférences","Select Theme According to Your Preferences");
      fr_eng.put("Sombre","Dark");
      fr_eng.put("Clair","Light");
      fr_eng.put("Sélectionner un Language","Select a Language");
      fr_eng.put("English", "Anglais");
      fr_eng.put("Français","French");
      
      
      english.setSelected(true);
      
      english.setOnAction(e->{
         for (Labeled element : registeredLabel) {
          String Text = element.getText();
          if (fr_eng.containsKey(Text)) {
            element.setText(fr_eng.get(Text));
        }
          
      }
      });
     
      french.setOnAction(e->{
          for (Labeled element : registeredLabel) {
          String Text = element.getText();
          if (eng_fr.containsKey(Text)) {
            element.setText(eng_fr.get(Text));
        }
    }
      });
    
      
      VBox language= new VBox(10,text,english,french);
      language.setPadding(new Insets(10));
      
      Stage stage= new Stage();
      stage.setTitle("Language Preference"); 
      Scene scene= new Scene(language,300,300);  
      scene.getStylesheets().add("style.css");
      stage.setScene(scene);
      stage.show();  
      
      Settings settings= new Settings();
      settings.registerScene(scene);
             
    }
    
}
    

