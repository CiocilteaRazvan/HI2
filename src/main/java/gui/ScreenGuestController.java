package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ScreenGuestController implements Initializable, ControlledScreenI {
    ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //delete this shit
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    public void goToScreen1(ActionEvent event) {
        myController.setScreen(Main.screenLoginID);
    }

    String product, price, description;

}
