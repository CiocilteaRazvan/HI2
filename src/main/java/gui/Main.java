package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

import db.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(new File("src/main/java/gui/login.fxml").toURI().toURL());
        Parent root = loader.load();
        primaryStage.setTitle("Welcome to HIV");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        /********************************************************************************************
         * if Stock stock = new Stock();                                                            *
         * always close a database using stock.close() or basket.close() or credentials.close()     *
         * at the end of the program                                                                *
         ********************************************************************************************/

//        Stock stock = new Stock();
//        stock.addItem("Card You can use this to go places 10 130");
//        stock.printAllItems();
//        stock.updatePrice("Laptop 1500");
//        stock.removeItem("Card");
//        stock.printAllItems();

        //Basket basket = new Basket();
//        try {
//            stock.addToBasket("Laptop 1", basket); // needs to know the basket to put item into
//        } catch (Exception e) {
//            Database.printException(e);
//        }

//        stock.close();
        //basket.close();
        //stock.printAllItems();
        //basket.printAllItems();

//        try {
//            basket.removeItem("Laptop 2", stock);   // needs to know the stock to put items back into
//        } catch (Exception e) {
//            Database.printException(e);
//            // removed more than is in basket
//            // similar to add more than is in stock
//        }
//
//        try {
//            basket.removeItem("Laptop 1", stock);
//        } catch (Exception e) {
//            Database.printException(e);
//        }
    }
}