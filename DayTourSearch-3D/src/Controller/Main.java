package Controller;

import Controller.UserLogin;
import Data.BookingDataConnection;
import Data.CustomerDataConnection;
import Controller.DayTripController;
import Model.Booking;
import Model.Customer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;


public class Main extends Application {

    private Customer customer;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Get the user
        UserLogin login = new UserLogin();
        Pair<String, String> user = login.getUser();
        // If pressed cancel or invalid user, then login system reappears
        if(user != null){
            CustomerDataConnection customerConnection = new CustomerDataConnection();
            customer = customerConnection.getCustomer(user.getKey(), user.getValue());
            if(customer == null){
                start(primaryStage);
                return;
            }
        }
        else{
            start(primaryStage);
            return;
        }

        // Load and show the interface of the search engine
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/dayTripSearchView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Day Tour Search");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        // Get this customers bookings from db to the customers instance
        BookingDataConnection bookingDataConn = new BookingDataConnection();
        ObservableList<Booking> bookings = bookingDataConn.getBookings(customer.getCustomerId());
        for(Booking booking : bookings) customer.addBooking(booking);

        // Initialize the search engine interface and attributes for this customer
        DayTripController dayTripController = loader.getController();
        dayTripController.initData(customer);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
