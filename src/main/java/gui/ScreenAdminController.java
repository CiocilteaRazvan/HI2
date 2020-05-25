package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ScreenAdminController implements Initializable, ControlledScreenI {
    ScreensController myController;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void goToScreen1(ActionEvent event) {
        myController.setScreen(Main.screenLoginID);
    }

    @FXML
    public void goToScreen2(ActionEvent event) {
        myController.setScreen(Main.screenGuestID);
    }
}
