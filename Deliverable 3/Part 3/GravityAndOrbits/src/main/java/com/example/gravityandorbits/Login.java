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

//import static org.junit.Assert.assertEquals;

public class Login {

    private ArrayList<String> usernames = new ArrayList<>();
    private ArrayList<String> passwords = new ArrayList<>();
    private int key = 0; //The key to determining if login is successful
    private Runnable onLoginSuccess;

    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }


    public Parent showLoginScreen() {
        //Dummy details for demonstration purposes
        usernames.add("test");
        passwords.add("12345678");

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
            button.setOnAction(e-> {
                if (verifyLogin(enterUsername.getText(), enterPassword.getText())) {
                    System.out.println("Correct username and password!");
                    key = 1;
                    if (onLoginSuccess != null) {
                        onLoginSuccess.run();
                    }
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

        vb.getChildren().addAll(welcome, usernameLabel, enterUsername,
                passwordLabel, enterPassword, button, createAccount);

        createAccount.setOnMouseClicked(e-> {
            Label create = new Label("Create an account");
            create.setScaleX(3); create.setScaleY(3);
            Text usernameLabel1 = new Text("Enter username:");
            usernameLabel1.setScaleX(1.5); usernameLabel1.setScaleY(1.5);
            Text passwordLabel1 = new Text("Enter password:");
            passwordLabel1.setScaleX(1.5); passwordLabel1.setScaleY(1.5);
            TextField username = new TextField();
            username.setPromptText("Type username...");
            PasswordField password = new PasswordField();
            password.setPromptText("Type password...");
            username.setScaleX(2); username.setScaleY(2);
            password.setScaleX(2); password.setScaleY(2);
            Label msg = new Label();
            msg.setScaleX(1.5); msg.setScaleY(1.5);
            msg.setTextFill(Color.RED);
            username.setMaxWidth(150);
            password.setMaxWidth(150);
            Button registerButton = new Button("Register account");
            registerButton.setScaleX(2); registerButton.setScaleY(2);
            registerButton.setTextFill(Color.BLACK);
            Button back = new Button("Back");
            back.setScaleX(2); back.setScaleY(2);
            back.setTextFill(Color.BLUE);
            
            Settings accountCreation= new Settings();
            accountCreation.registerLabel(create);
            accountCreation.registerLabel(msg);
            accountCreation.registerLabel(registerButton);
            accountCreation.registerLabel(back);
            accountCreation.registerText(usernameLabel1);
            accountCreation.registerText(passwordLabel1);
            accountCreation.registerTextField(username);
            accountCreation.registerTextField(password);
            
            back.setOnAction(e2-> {
                vb.getChildren().removeAll(create, back, usernameLabel1, username, passwordLabel1,
                        password, msg, registerButton);
                vb.getChildren().addAll(welcome, usernameLabel, enterUsername,
                        passwordLabel, enterPassword, button, createAccount);
            });

            vb.getChildren().removeAll(welcome, usernameLabel, enterUsername,
                    passwordLabel, enterPassword, button, createAccount, error);
            vb.getChildren().addAll(create, back,usernameLabel1, username, passwordLabel1,
                    password, registerButton, msg);

            registerButton.setOnAction(e1-> {
                if (username.getText().isEmpty() || password.getText().isEmpty()) {
                    msg.setText("Username and password required");
                    Settings message = new Settings();
                    message.registerLabel(msg);
                    return;
                }

                if (checkIfUserExists(username.getText())) {
                    msg.setText("Username already exists.");
                    Settings message = new Settings();
                    message.registerLabel(msg);
                }
                else {
                    usernames.add(username.getText());
                    passwords.add(password.getText());
                }        
                vb.getChildren().removeAll(create, back, usernameLabel1, username, passwordLabel1,
                        password, msg, registerButton);
                vb.getChildren().addAll(welcome, usernameLabel, enterUsername,
                        passwordLabel, enterPassword, button, createAccount);
                vb.getChildren().add(error);
                error.setText("Registration successful!");
                error.setFill(Color.GREEN);
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> {
                    vb.getChildren().remove(error); // Remove the warning message after 3 seconds
                    error.setText("Incorrect username or password. Try again.");
                    error.setFill(Color.RED);
                });
                pause.play();
            });


        });
        
         Settings LoginSettings= new Settings();
         LoginSettings.registerLabel(welcome);
         LoginSettings.registerText(usernameLabel);
         LoginSettings.registerText(passwordLabel);
         LoginSettings.registerText(error);
         LoginSettings.registerTextField(enterUsername);
         LoginSettings.registerTextField(enterPassword);
         LoginSettings.registerLabel(button);
         LoginSettings.registerText(createAccount);
         
        StackPane pane = new StackPane(vb);
        vb.setAlignment(Pos.CENTER);
        vb.setLayoutX(ui.SCREENWIDTH / 2);
        vb.setLayoutY(ui.SCREENHEIGHT / 2);
        vb.setSpacing(80);

        if (!validateLogin()) {
            return pane;
        }
        else return ui.initialize();

    }

    public boolean validateLogin() {
        if (key == 1) { // Validates if login was successful
            return true;
        }
        else return false;
    }

    private boolean checkUsername(String username) {
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPassword(String password) {
        for (int i = 0; i < passwords.size(); i++) {
            if (passwords.get(i).equals(password)) {
                return true;
            }
        }
        return false;
    }

    protected boolean verifyLogin(String username, String password) {
        if (checkUsername(username) && checkPassword(password)) {
            return true;
        }
        else return false;
    }

    private boolean checkIfUserExists(String username) {
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equals(username)) {
                return true;
            }
        }
        return false;
    }

}