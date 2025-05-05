/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gravityandorbits;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Angela
 */
public class Settings{
       
    private static List<Scene> registeredScenes= new ArrayList<>();
    public static  List<Labeled> registeredLabel=new ArrayList<>();
    private static List<Menu> registeredMenu= new ArrayList<>();
    private static List<MenuItem> registeredMenuItem= new ArrayList<>();
    private static List<TextField> registeredTextField= new ArrayList<>();
    private static List<Text> registeredText= new ArrayList<>();
   
    public void registerScene(Scene scene){
        registeredScenes.add(scene);
        //TO HAVE EVERY SCENE ON THE SAME THEME/LANGUAGE EVEN THOUGH NOT OPENED
        applyThemeToScene(scene);
        applyLanguageToAllLabels();
        applyLanguageToMenuBar();
        applyLanguageToTextField();
        applyLanguageToText();
    }

    public void registerLabel(Labeled label){
         registeredLabel.add(label);
          applyLanguageToAllLabels();
     }
     
    public void registerMenu(Menu menu){
        registeredMenu.add(menu); 
    }
    
    public void registerMenuItems(MenuItem menuItem){
        registeredMenuItem.add(menuItem);
        applyLanguageToMenuBar();
    }
    
    public void registerTextField(TextField textField){
        registeredTextField.add(textField);
        applyLanguageToTextField();
    }
    
    public void registerText(Text text){
        registeredText.add(text);
        applyLanguageToText();
    }
     
    public static void theme(){
      Label text= new Label("Select Theme According to Your Preferences");  
      RadioButton dark= new RadioButton("Dark");  
      RadioButton light= new RadioButton("Light");
      ToggleGroup radioGroup= new ToggleGroup();
      dark.setToggleGroup(radioGroup);
      light.setToggleGroup(radioGroup);
      
      Settings appearancetext= new Settings();
      appearancetext.registerLabel(text);
      appearancetext.registerLabel(light);
      appearancetext.registerLabel(dark);
      
      String currentTheme= loadTheme();
      if("dark".equals(currentTheme)){
          dark.setSelected(true);
      }else{
          light.setSelected(true);
      }
      
      dark.setOnAction(e->{
        for(Scene Mainscene:registeredScenes){
        Mainscene.getStylesheets().clear();
        Mainscene.getStylesheets().add("darkmode.css");
        }  
        saveTheme("dark");
      });
      
      light.setOnAction(e->{
        for(Scene Mainscene:registeredScenes){
        Mainscene.getStylesheets().clear();
        Mainscene.getStylesheets().add("style.css");
        } 
       saveTheme("light");
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
    
    public static final Map<String,String> eng_fr=new HashMap<>();
    public static final Map<String,String> fr_eng=new HashMap<>();
   
    static{
      eng_fr.put("Welcome to Gravity and Orbits!", "Bienvenue sur Gravité et Orbites");
      eng_fr.put("Enter username:","Entrez le nom d'utilisateur:");
      eng_fr.put("Type username...", "Tapez le nom d'utilisateur");
      eng_fr.put("Enter password:","Entrez le mot de passe:");
      eng_fr.put("Type password...","Tapez le mot de passe...");
      eng_fr.put("Login", "Connexion");
      eng_fr.put("Incorrect username or password. Try again.", "Nom d'utilisateur ou mot de passe est incorrect. Essayez à nouveau.");
      eng_fr.put("Don't have an account? Create one!","Pas de compte ? Créez-en un!");
      eng_fr.put("Create an account","Créer un compte");
      eng_fr.put("Register account", "S'inscrire");
      eng_fr.put("Back", "Retour");
      eng_fr.put("Username and password required","Nom d'utilisateur et mot de passe requis");
      eng_fr.put("Username already exists.","Le nom d'utilisateur existe déjà.");
      eng_fr.put("Registration successful!","Inscription réussie!");
      //MAIN APP
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
      //HELP CENTER
      eng_fr.put("Customizing Planets","Personnaliser les Planètes");
      eng_fr.put("Display Options","Options d'affichage");
      eng_fr.put("Simulation Controls","Contrôles de Simulation");
      eng_fr.put("Project Management","Gestion de Projet");
      eng_fr.put("This application will help you understand the theory of gravitational force between planets. You will be able to see how the force is affected by different factors with vectors and path like down below.","Cette application vous aidera à comprendre la théorie de la force gravitationnelle entre les planètes. Vous pourrez voir comment la force est affectée par différents facteurs à l'aide de vecteurs et de trajectoires comme ci-dessous.");
      eng_fr.put("FEATURES","FONCTIONNALITÉS");
      eng_fr.put("Add Planets (up to 5 planets): \nClick on 'Add Custom Planet' or select from presets options \nChange Parameters: \nInput a new mass/velocity/radius on the right side ","Ajouter des planètes (jusqu'à 5 planètes) : \nCliquez sur 'Ajouter une planète personnalisée' ou sélectionnez parmi les options prédéfinies. \nChanger les paramètres : \nEntrer une nouvelle masse/vitesse/rayon à droite.");
      eng_fr.put("""
                 Show Grid:
                 Enable the checkbox 'Show Grid'.
                 Show Path:
                 Enable the checkbox 'Show Path'.
                 Show Vectors:
                 Enable the corresponding checkbox 'Show Gravity Vectors/Show Velocity Vectors'.""", """
                 Montrer la Grille:
                 Clicker la case 'Montrer la Grille'.
                 Montrer la Trajectoire:
                 Clicker la case 'Montrer la Trajectoire'.
                 Montrer les Vecteurs:
                 Clicker la case correspondant 'Montrer les vecteurs de gravité/Montrer les vecteurs de vitesse'.""");
      eng_fr.put("""
                 Change Scale:
                 Interact with the slider 'Scale'.
                 Change Time:
                 Interact with the slider 'Time'.
                 Start/Pause Animation:
                 Click on the corresponding buttons 'Start/Pause'.
                 Reset Animation:
                 Click on the button 'Reset'. """,
                 """
                 Changer l'Échelle:
                 Interagissez avec le curseur 'Échelle'.
                 Change Time:
                 Interagissez avec le curseur 'Temps'.
                 Commencer/Arrêter l'Animation:
                 Clicker sur le button correspondant 'Commencer/Arrêter'.
                 Réinitialiser l'Animation:
                 Clicker sur le button 'Réinitialiser'. """);
      eng_fr.put("""
                 Save Project:
                 Click on File and then click on save.
                 Load Project:
                 Click on File and then click on load
                 Choose the file from your File Explorer you want to open
                 Delete Project:
                 Click on File and then click on delete.
                 Choose the file from your File Explorer you want to delete.""",
                """
                Sauvegarder le Project:
                Clicker sur Fichier puis clicker sur sauvegarder.
                Charger le Project:
                Clicker sur Fichier puis clicker sur charger
                Choisir le ficher que vous voulez ouvrir.
                Supprimer le Project:
                Clicker sur Ficher puis clicker sur supprimer.
                Choisir le ficher que vous voulez supprimer.""");
      eng_fr.put("Theme/Language","Thème/Language");
      eng_fr.put("""
                 Change Theme:
                 Adjust theme according to your preferences.
                 Change Language:
                 Adjust language according to your preferences.""",
                 """
                 Changer le thème:
                 Ajuster le thème selon vos préférences.
                 Changer le language:
                 Ajuster le language selon vos préférences""");
      //MENUBAR
      eng_fr.put("Exit","Quitter");
      eng_fr.put("File","Ficher");
      eng_fr.put("Settings","Paramètres");
      eng_fr.put("Help","Aide");
      eng_fr.put("Exit the application", "Quitter l'application");
      eng_fr.put("Save","Sauvegarder");
      eng_fr.put("Load","Charger");
      eng_fr.put("Delete","Supprimer");
      eng_fr.put("Theme","Thème");
      eng_fr.put("Language Preference","Language de Préférence");
      //PLANETS
      eng_fr.put("Earth","Terre");
      eng_fr.put("Moon","Lune");
      eng_fr.put("Sun","Soleil");
      eng_fr.put("Initial Velocity: ", "Vitesse Initiale: ");
      eng_fr.put("Mass: ","Masse: ");
      eng_fr.put("Radius Multiplier: ","Multiplicateur de Rayon: ");
      eng_fr.put("Done","Terminé");
      eng_fr.put("Select Planet Type","Sélectionner le type de planète");
      eng_fr.put("You must choose a planet type","Vous devez choisir un type de planète");
      eng_fr.put("Enter Mass...","Entrez la masse...");
      eng_fr.put("Enter velocity...","Entrez la vitesse...");
      eng_fr.put("Enter radius...","Entrez le rayon...");
      eng_fr.put("Earth parameters","Paramètres de la Terre");
      eng_fr.put("Sun parameters","Paramètres du Soleil");
      eng_fr.put("Moon parameters","Paramètres de la Lune");
      eng_fr.put("Mars parameters","Paramètres de Mars");
      eng_fr.put("Venus parameters","Paramètres de Venus");
      eng_fr.put("Neptune parameters","Paramètres de Neptune");
      //EXIT INTERFACE
      eng_fr.put("Are you sure you want to exit the application?","Êtes-vous sûr de vouloir quitter l'application?");
      eng_fr.put("Yes","Oui");
      eng_fr.put("No","Non");
      
      
      //TRANSLATION FRENCH TO ENGLISH
      //LOGIN
      fr_eng.put("Bienvenue sur Gravité et Orbites","Welcome to Gravity and Orbits!");
      fr_eng.put("Entrez le nom d'utilisateur:","Enter username:");
      fr_eng.put("Tapez le nom d'utilisateur","Type username...");
      fr_eng.put("Entrez le mot de passe:","Enter password:");
      fr_eng.put("Tapez le mot de passe...","Type password...");
      fr_eng.put("Connexion","Login");
      fr_eng.put("Nom d'utilisateur ou mot de passe est incorrect. Essayez à nouveau.","Incorrect username or password. Try again.");
      fr_eng.put("Pas de compte ? Créez-en un!","Don't have an account? Create one!");
      fr_eng.put("Créer un compte","Create an account");
      fr_eng.put("S'inscrire","Register account");
      fr_eng.put("Retour","Back");
      fr_eng.put("Nom d'utilisateur et mot de passe requis","Username and password required");
      fr_eng.put("Le nom d'utilisateur existe déjà.","Username already exists.");
      fr_eng.put("Inscription réussie!","Registration successful!");
      //MAIN APP
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
      fr_eng.put("Anglais", "English");
      fr_eng.put("Français","French");
      //HELP CENTER
      fr_eng.put("Personnaliser les Planètes","Customizing Planets");
      fr_eng.put("Options d'affichage","Display Options");
      fr_eng.put("Contrôles de Simulation","Simulation Controls");
      fr_eng.put("Gestion de Projet","Project Management");
      fr_eng.put("Cette application vous aidera à comprendre la théorie de la force gravitationnelle entre les planètes. Vous pourrez voir comment la force est affectée par différents facteurs à l'aide de vecteurs et de trajectoires comme ci-dessous.","This application will help you understand the theory of gravitational force between planets. You will be able to see how the force is affected by different factors with vectors and path like down below.");
      fr_eng.put("FONCTIONNALITÉS","FEATURES");
      fr_eng.put("Ajouter des planètes (jusqu'à 5 planètes) : \nCliquez sur 'Ajouter une planète personnalisée' ou sélectionnez parmi les options prédéfinies. \nChanger les paramètres : \nEntrer une nouvelle masse/vitesse/rayon à droite.","Add Planets (up to 5 planets): \nClick on 'Add Custom Planet' or select from presets options \nChange Parameters: \nInput a new mass/velocity/radius on the right side ");
      fr_eng.put("""
                 Montrer la Grille:
                 Clicker la case 'Montrer la Grille'.
                 Montrer la Trajectoire:
                 Clicker la case 'Montrer la Trajectoire'.
                 Montrer les Vecteurs:
                 Clicker la case correspondant 'Montrer les vecteurs de gravité/Montrer les vecteurs de vitesse'.""","""
                 Show Grid:
                 Enable the checkbox 'Show Grid'.
                 Show Path:
                 Enable the checkbox 'Show Path'.
                 Show Vectors:
                 Enable the corresponding checkbox 'Show Gravity Vectors/Show Velocity Vectors'.""");
      fr_eng.put("""
                 Changer l'Échelle:
                 Interagissez avec le curseur 'Échelle'.
                 Change Time:
                 Interagissez avec le curseur 'Temps'.
                 Commencer/Arrêter l'Animation:
                 Clicker sur le button correspondant 'Commencer/Arrêter'.
                 Réinitialiser l'Animation:
                 Clicker sur le button 'Réinitialiser'. ""","""
                 Change Scale:
                 Interact with the slider 'Scale'.
                 Change Time:
                 Interact with the slider 'Time'.
                 Start/Pause Animation:
                 Click on the corresponding buttons 'Start/Pause'.
                 Reset Animation:
                 Click on the button 'Reset'. """);
      fr_eng.put("""
                 Sauvegarder le Project:
                 Clicker sur Fichier puis clicker sur sauvegarder.
                 Charger le Project:
                 Clicker sur Fichier puis clicker sur charger
                 Choisir le ficher que vous voulez ouvrir.
                 Supprimer le Project:
                 Clicker sur Ficher puis clicker sur supprimer.
                 Choisir le ficher que vous voulez supprimer.""",
                 """
                 Save Project:
                 Click on File and then click on save.
                 Load Project:
                 Click on File and then click on load
                 Choose the file from your File Explorer you want to open
                 Delete Project:
                 Click on File and then click on delete.
                 Choose the file from your File Explorer you want to delete.""");
      fr_eng.put("Thème/Language","Theme/Language");
      fr_eng.put("""
                 Changer le thème:
                 Ajuster le thème selon vos préférences.
                 Changer le language:
                 Ajuster le language selon vos préférences""","""
                 Change Theme:
                 Adjust theme according to your preferences.
                 Change Language:
                 Adjust language according to your preferences.""");
      //MENUBAR
      fr_eng.put("Quitter", "Exit");
      fr_eng.put("Ficher","File");
      fr_eng.put("Paramètres","Settings");
      fr_eng.put("Aide","Help");
      fr_eng.put( "Quitter l'application","Exit the application");
      fr_eng.put("Sauvegarder","Save");
      fr_eng.put("Charger","Load");
      fr_eng.put("Supprimer","Delete");
      fr_eng.put("Thème","Theme");
      fr_eng.put("Language de Préférence","Language Preference");
      //PLANETS
      fr_eng.put("Terre","Earth");
      fr_eng.put("Lune","Moon");
      fr_eng.put("Soleil","Sun");
      fr_eng.put("Vitesse Initiale: ","Initial Velocity: ");
      fr_eng.put("Masse: ","Mass: ");
      fr_eng.put("Multiplicateur de Rayon: ","Radius Multiplier: ");
      fr_eng.put("Terminé","Done");
      fr_eng.put("Sélectionner le type de planète","Select Planet Type");
      fr_eng.put("Vous devez choisir un type de planète","You must choose a planet type");
      fr_eng.put("Entrez la masse...","Enter Mass...");
      fr_eng.put("Entrez la vitesse...","Enter velocity...");
      fr_eng.put("Entrez le rayon...","Enter radius...");
      fr_eng.put("Paramètres de la Terre","Earth parameters");
      fr_eng.put("Paramètres du Soleil","Sun parameters");
      fr_eng.put("Paramètres de la Lune","Moon parameters");
      fr_eng.put("Paramètres de Mars","Mars parameters");
      fr_eng.put("Paramètres de Venus","Venus parameters");
      fr_eng.put("Paramètres de Neptune","Neptune parameters");
      //EXIT INTERFACE
      fr_eng.put("Êtes-vous sûr de vouloir quitter l'application?","Are you sure you want to exit the application?");
      fr_eng.put("Oui","Yes");
      fr_eng.put("Non","No");
    }
    
    
    public static void language(){
      Label text= new Label("Select a Language");  
      RadioButton english= new RadioButton("English");
      RadioButton french= new RadioButton("French");
      ToggleGroup radioGroup= new ToggleGroup();
      english.setToggleGroup(radioGroup);
      french.setToggleGroup(radioGroup);
      
      Settings languages= new Settings();
      languages.registerLabel(text);
      languages.registerLabel(english);
      languages.registerLabel(french);
      
      String currentLanguage=loadLanguage();
       if("french".equals(currentLanguage)){
          french.setSelected(true);
      }else{
          english.setSelected(true);
      }
      
      english.setOnAction(e->{
         for (Labeled element : registeredLabel) {
          String Text = element.getText();
          if (fr_eng.containsKey(Text)) {
            element.setText(fr_eng.get(Text));
        } 
      }
         for(Menu menus:registeredMenu){
             String title=menus.getText();
            if (fr_eng.containsKey(title)) {
            menus.setText(fr_eng.get(title));
         }
       } 
          for(MenuItem menuItems:registeredMenuItem){
             String subtitle=menuItems.getText();
            if (fr_eng.containsKey(subtitle)) {
            menuItems.setText(fr_eng.get(subtitle));
         }
       }
        for(TextField tf:registeredTextField){
             String answer=tf.getPromptText();
            if (fr_eng.containsKey(answer)) {
            tf.setPromptText(fr_eng.get(answer));
        }
       }
        for(Text t:registeredText){
            String sentence=t.getText();
            if (fr_eng.containsKey(sentence)) {
            t.setText(fr_eng.get(sentence));
        }
       }
         saveLanguage("english"); 
      });
     
      french.setOnAction(e->{
         for (Labeled element : registeredLabel) {
          String Text = element.getText();
          if (eng_fr.containsKey(Text)) {
            element.setText(eng_fr.get(Text));
        }
    }
           for(Menu menus:registeredMenu){
             String title=menus.getText();
            if (eng_fr.containsKey(title)) {
            menus.setText(eng_fr.get(title));
         }
     }
            for(MenuItem menuItems:registeredMenuItem){
             String subtitle=menuItems.getText();
            if (eng_fr.containsKey(subtitle)) {
            menuItems.setText(eng_fr.get(subtitle));
         }
       }
           for(TextField tf:registeredTextField){
             String answer=tf.getPromptText();
            if (eng_fr.containsKey(answer)) {
            tf.setPromptText(eng_fr.get(answer));
        }
       }
            for(Text t:registeredText){
            String sentence=t.getText();
            if (eng_fr.containsKey(sentence)) {
            t.setText(eng_fr.get(sentence));
        }
       }
          saveLanguage("french");
      });
    
      
      VBox language= new VBox(10,text,english,french);
      language.setPadding(new Insets(10));
      
      Stage stage= new Stage();
      stage.setTitle("Language Preference"); 
      Scene scene= new Scene(language,300,300);  
      scene.getStylesheets().add("style.css");
      stage.setScene(scene);
      stage.show();  
      
      Settings settings = new Settings();
      settings.registerScene(scene);
             
    }
    
    //TO KEEP THE LATEST CLICKED RADIO BUTTON EVEN AFTER CLOSING THE SCENE
    public static void saveTheme(String theme){
        try(FileWriter writer= new FileWriter("theme.txt")){
            writer.write(theme);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static String loadTheme(){
        try(BufferedReader reader= new BufferedReader(new FileReader("theme.txt"))){
            return reader.readLine();
        } catch (IOException e) {
        return "light"; 
    }
}
     public static void applyThemeToScene(Scene scene) {
        String theme = loadTheme();

        if("dark".equals(theme)){
        scene.getStylesheets().clear();
        scene.getStylesheets().add("darkmode.css");
        }else{
        scene.getStylesheets().clear();
        scene.getStylesheets().add("style.css");
        }
    }

     public static void saveLanguage(String lang){
        try(FileWriter writer= new FileWriter("language.txt")){
            writer.write(lang);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static String loadLanguage(){
        try(BufferedReader reader= new BufferedReader(new FileReader("language.txt"))){
            return reader.readLine();
        } catch (IOException e) {
        return "english"; 
    }
}
 
    public static void applyLanguageToAllLabels() {
    String language = loadLanguage(); 

    if ("english".equals(language)) {
        for (Labeled element : registeredLabel) {
            String text = element.getText();
            if (fr_eng.containsKey(text)) {
                element.setText(fr_eng.get(text)); 
            }
        }
    } else if ("french".equals(language)) {
        for (Labeled element : registeredLabel) {
            String text = element.getText();
            if (eng_fr.containsKey(text)) {
                element.setText(eng_fr.get(text));
            }
        }
    }
}
  public static void applyLanguageToMenuBar(){
      String language= loadLanguage();
      if("english".equals(language)){
      for(Menu menus:registeredMenu){
            String title=menus.getText();
            if (fr_eng.containsKey(title)) {
            menus.setText(fr_eng.get(title));
         }
       } 
          for(MenuItem menuItems:registeredMenuItem){
             String subtitle=menuItems.getText();
            if (fr_eng.containsKey(subtitle)) {
            menuItems.setText(fr_eng.get(subtitle));
         }
       }
      
  }else if("french".equals(language)){
      for(Menu menus:registeredMenu){
             String title=menus.getText();
            if (eng_fr.containsKey(title)) {
            menus.setText(eng_fr.get(title));
         }
     }
            for(MenuItem menuItems:registeredMenuItem){
             String subtitle=menuItems.getText();
            if (eng_fr.containsKey(subtitle)) {
            menuItems.setText(eng_fr.get(subtitle));
         }
       }
  }
  }
  public static void applyLanguageToTextField(){
      String language= loadLanguage();
      if ("english".equals(language)) {
        for (TextField tf:registeredTextField) {
            String text = tf.getPromptText();
            if (fr_eng.containsKey(text)) {
                tf.setPromptText(fr_eng.get(text));
            }
        }
    } else if ("french".equals(language)) {
        for (TextField tf:registeredTextField) {
            String text = tf.getPromptText();
            if (eng_fr.containsKey(text)) {
                tf.setPromptText(eng_fr.get(text));
            }
        }
    }
      
  }
  public static void applyLanguageToText(){
      String language= loadLanguage();
      if ("english".equals(language)) {
        for (Text t:registeredText) {
            String text = t.getText();
            if (fr_eng.containsKey(text)) {
                t.setText(fr_eng.get(text));
            }
        }
    } else if ("french".equals(language)) {
        for (Text t:registeredText) {
            String text = t.getText();
            if (eng_fr.containsKey(text)) {
                t.setText(eng_fr.get(text));
            }
        }
    }
      
  }

}
