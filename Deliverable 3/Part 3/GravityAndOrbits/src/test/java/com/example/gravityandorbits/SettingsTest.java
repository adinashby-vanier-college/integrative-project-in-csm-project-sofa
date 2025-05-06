/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gravityandorbits;

import java.io.*;
import java.io.IOException;
import javafx.scene.control.Labeled;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
/**
 *
 * @author Angela
 */
public class SettingsTest {

    /* Add these VM options for running tests:

    --add-opens org.junit.platform.commons/org.junit.platform.commons.util=ALL-UNNAMED
    --add-opens org.junit.platform.commons/org.junit.platform.commons.logging=ALL-UNNAMED

     */
    @Test
     public void saveThemeTest() throws IOException{
        String expected="light";
        
        Settings.saveTheme(expected);
        
        File file=new File("theme.txt");
        assertTrue(file.exists());
        
        try(BufferedReader reader= new BufferedReader(new FileReader(file))){
            String actual=reader.readLine();
            assertEquals(expected,actual);  
        }
        file.delete(); 
    }
    
    @Test
    public void loadThemeTest() throws IOException{
        String expected="light";
        
        try(FileWriter fw= new FileWriter("theme.txt")){
            fw.write(expected);
        }
        
        String actual= Settings.loadTheme();    
        assertEquals(expected,actual);
    }
    
    
    @Test
    public void saveLanguageTest() throws IOException{
        String expected="english";
        
        Settings.saveLanguage(expected);
        
        File file=new File("language.txt");
        assertTrue(file.exists());
        
        try(BufferedReader reader= new BufferedReader(new FileReader(file))){
            String actual=reader.readLine();
            assertEquals(expected,actual);  
        }
        file.delete(); 
    }
    
    @Test
    public void loadLanguageTest() throws IOException{
       String expected="english";
        
        try(FileWriter fw= new FileWriter("language.txt")){
            fw.write(expected);
        }
        
        String actual= Settings.loadLanguage();    
        assertEquals(expected,actual);
    } 
    
    @BeforeEach
    public void setup(){
    Settings.registeredLabel.clear();    
    Settings.eng_fr.clear();
    Settings.fr_eng.clear();
      
    Settings.eng_fr.put("Welcome","Bienvenue");
    Settings.fr_eng.put("Bienvenue","Welcome");
   
 }
     
    @Test
    public void applyLanguageToAllLabelsTest()throws IOException{
       String language="english" ;
       try(FileWriter fw= new FileWriter("language.txt")){
            fw.write(language);
        }
       Settings.loadLanguage();
       Settings.applyLanguageToAllLabels();

       for(Labeled label:Settings.registeredLabel){
           assertEquals("Welcome",label.getText());
       }
    
}
}