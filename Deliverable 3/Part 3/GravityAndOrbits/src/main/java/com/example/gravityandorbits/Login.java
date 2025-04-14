package com.example.gravityandorbits;

import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Login {

    private ArrayList<String> usernames = new ArrayList<>();
    private ArrayList<String> passwords = new ArrayList<>();
    private int key = 0;

    public Parent login() {
        //Dummy details for demonstration purposes
        usernames.add("test");
        passwords.add("123");

        UI ui = new UI();
        VBox vb = new VBox();
        Label welcome = new Label("Welcome to Gravity and Orbits!");
        welcome.setScaleX(3);
        welcome.setScaleY(3);
        Text usernameLabel = new Text("Enter username:");
        Text passwordLabel = new Text("Enter password:");
        Text error = new Text("Incorrect username or password. Try again.");
        error.setScaleX(1.5); error.setScaleY(1.5);
        error.setFill(Color.RED);
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
        System.out.println("ArrayList size: " + usernames.size());
            button.setOnAction(e-> {
                if (verifyLogin(enterUsername.getText(), enterPassword.getText())) {
                    System.out.println("Correct username and password!");
                }
                else {
                    if (!vb.getChildren().contains(error)) {
                        vb.getChildren().add(error);
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {
                            vb.getChildren().remove(error); // Remove the warning message after 3 seconds
                        });
                        pause.play();
                    }
                }
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

        vb.getChildren().addAll(welcome, usernameLabel, enterUsername,
                passwordLabel, enterPassword, button, createAccount);
        StackPane pane = new StackPane(vb);
        vb.setAlignment(Pos.CENTER);
        vb.setLayoutX(ui.SCREENWIDTH / 2);
        vb.setLayoutY(ui.SCREENHEIGHT / 2);
        vb.setSpacing(80);

        return pane;

    }
    public boolean validateLogin() {
        if (key == 1) { // Validates if login was successful
            return true;
        }
        else return false;
    }

    public boolean checkUsername(String username) {
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPassword(String password) {
        for (int i = 0; i < passwords.size(); i++) {
            if (passwords.get(i).equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyLogin(String username, String password) {
        if (checkUsername(username)&& checkPassword(password)) {
            return true;
        }
        else return false;
    }

}