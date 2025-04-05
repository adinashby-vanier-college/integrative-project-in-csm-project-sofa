package com.example.gravityandorbits;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class FileManager {

    public static void loadAnimation(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file");
        fileChooser.showOpenDialog(stage);
        fileChooser.setInitialDirectory(new File("C://"));
    }

    public static void saveAnimation() {
        //Add functionality
    }

    public static void deleteAnimation(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file to delete");
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile.delete()) {
            System.out.println("File deleted.");
        }
        else System.out.println("Failed to delete file.");
        fileChooser.setInitialDirectory(new File("C://"));
    }
}
