package com.example.gravityandorbits;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Login {

    private int key = 0;

    public Parent login() {
        UI ui = new UI();
        Label welcome = new Label("Welcome to Gravity and Orbits!");
        welcome.setScaleX(3);
        welcome.setScaleY(3);
        Text usernameLabel = new Text("Enter username:");
        Text passwordLabel = new Text("Enter password:");
        usernameLabel.setScaleX(1.5); usernameLabel.setScaleY(1.5);
        passwordLabel.setScaleX(1.5); passwordLabel.setScaleY(1.5);

        TextField enterUsername = new TextField();
        PasswordField enterPassword = new PasswordField();
        enterUsername.setPromptText("Type username...");
        enterPassword.setPromptText("Type password...");
        enterUsername.setScaleX(2); enterUsername.setScaleY(2);
        enterPassword.setScaleX(2); enterPassword.setScaleY(2);
        enterUsername.setMaxWidth(150);
        enterPassword.setMaxWidth(150);

        Button button = new Button("Login");
        button.setScaleX(2); button.setScaleY(2);
        button.setTextFill(Color.BLUE);
        button.setOnAction(e-> {
            // Will add functionality later
        });


        Text createAccount = new Text("Don't have an account? Create one!");
        createAccount.setFill(Color.DEEPSKYBLUE);
        createAccount.setScaleX(1.3); createAccount.setScaleY(1.3);
        createAccount.setOnMouseEntered(e-> {
            createAccount.getScene().setCursor(Cursor.HAND);
        });
        createAccount.setOnMouseExited(e-> {
            createAccount.getScene().setCursor(Cursor.DEFAULT);
        });
        createAccount.setOnMouseClicked(e-> {
            System.out.println("Button clicked.");
            // Will add functionality later
        });

        VBox vb = new VBox(welcome, usernameLabel, enterUsername,
                passwordLabel, enterPassword, button, createAccount);
        StackPane pane = new StackPane(vb);
        vb.setAlignment(Pos.CENTER);
        vb.setLayoutX(ui.SCREENWIDTH / 2);
        vb.setLayoutY(ui.SCREENHEIGHT / 2);
        vb.setSpacing(100);

        return pane;

    }
    public boolean validateLogin() {
        if (key == 1) { // Validates if login was successful
            return true;
        }
        else return false;
    }

}