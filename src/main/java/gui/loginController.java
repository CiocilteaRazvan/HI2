package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import db.*;

public class loginController implements Initializable, ControlledScreenI {
    ScreensController myController;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

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
            if (userType == 1) {
                goToScreen3();
            }
            else if (userType == 2) {
                goToScreen4();
            }
        }
        catch (Exception e) {
            Database.printException(e);
        }
    }
    public void goToScreen2() {
        myController.setScreen(Main.screenGuestID);
    }
    public void goToScreen3() {
        myController.setScreen(Main.screenUserID);
    }

    public void goToScreen4() {
        myController.setScreen(Main.screenAdminID);
    }

    public void pressLoginAsGuest(ActionEvent event) {
        System.out.println("Logged in as guest");
        goToScreen2();
    }

}