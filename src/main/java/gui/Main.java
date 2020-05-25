package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    /*
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/gui/login.fxml").toURI().toURL());
        Parent root = loader.load();
        primaryStage.setTitle("Welcome to HIV");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    */
    public static String screenLoginID = "screen1";
    public static String screenLoginFile = "src/main/java/gui/login.fxml";

    public static String screenGuestID = "screen2";
    public static String screenGuestFile = "src/main/java/gui/ScreenGuest.fxml";

    public static String screenUserID = "screen3";
    public static String screenUserFile = "src/main/java/gui/ScreenUser.fxml";

    public static String screenAdminID = "screen4";
    public static String screenAdminFile = "src/main/java/gui/ScreenAdmin.fxml";

    public static String screenBasketID = "screen5";
    public static String screenBasketFile = "src/main/java/gui/ScreenBasket.fxml";



     @Override
    public void start(Stage primaryStage) {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.screenLoginID, Main.screenLoginFile);
        mainContainer.loadScreen(Main.screenGuestID, Main.screenGuestFile);
        mainContainer.loadScreen(Main.screenUserID, Main.screenUserFile);
        mainContainer.loadScreen(Main.screenAdminID, Main.screenAdminFile);
        mainContainer.loadScreen(Main.screenBasketID, Main.screenBasketFile);

        mainContainer.setScreen(Main.screenLoginID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}