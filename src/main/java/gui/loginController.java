package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;
import db.*;

public class loginController {
    @FXML
    TextField username_login;
    @FXML
    PasswordField password_login;

    public void pressLoginButton(ActionEvent event) throws NoSuchAlgorithmException {
        String username = username_login.getText();
        String password = password_login.getText();

        if (password == null || password.isEmpty() ) {

            System.out.println("No password");  // MUST BE REPLACED WITH MESSAGE ON GUI
            return;
        }
        if (username == null || username.isEmpty()) {
            System.out.println("No username");  // MUST BE REPLACED WITH MESSAGE ON GUI
            return;
        }

        Credentials credentials = new Credentials();
        try {
            int userType = credentials.checkCredentials(username, password);
            System.out.println(userType);
        }
        catch (Exception e) {
            Database.printException(e);
        }
    }

    public void pressLoginAsGuest(ActionEvent event) {
        System.out.println("Logged in as guest");
    }






}