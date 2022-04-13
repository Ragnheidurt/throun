package Controller;

import Data.BookingDataConnection;
import Data.DayTripDataConnection;
import Data.ReviewDataConnection;
import Model.Booking;
import Model.Customer;
import View.ChangeBooking;
import View.GiveReview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CustomerController {

    // Interface attributes
    @FXML
    private Label fxCustomer;
    @FXML
    private TableView<Booking> fxTable;
    @FXML
    private TableColumn<Booking, String> fxTitleCol;
    @FXML
    private TableColumn<Booking, Integer> fxSeatsCol;
    @FXML
    private TableColumn<Booking, Integer> fxAmountCol;
    @FXML
    private TableColumn<Booking, String> fxDateCol;
    @FXML
    private TableColumn<Booking, String> fxTimeCol;
    @FXML
    private TableColumn<Booking, Integer> fxDurationCol;
    @FXML
    private TableColumn<Booking, String> fxActivityCol;
    @FXML
    private TableColumn<Booking, String> fxLocationCol;
    @FXML
    private TableColumn<Booking, String> fxLanguageCol;
    @FXML
    private TableColumn<Booking, Integer> fxMyRatingCol;


    // Data attributes
    private Customer customer;


    public void initData(Customer customer){
        fxCustomer.setText(customer.getUsername());
        this.customer = customer;

        // Get this cutomers bookings
        ArrayList<Booking> bookings = customer.getBookings();
        ObservableList<Booking> b = FXCollections.observableArrayList(bookings);

        for(Booking boooking : bookings){
            System.out.println(boooking.getDayTripId());
        }

        // Add to table
        fxTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        fxSeatsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfGuests"));
        fxAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        fxDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        fxTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        fxDurationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        fxActivityCol.setCellValueFactory(new PropertyValueFactory<>("activity"));
        fxLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        fxLanguageCol.setCellValueFactory(new PropertyValueFactory<>("language"));
        fxMyRatingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        fxTable.getItems().clear();
        fxTable.setItems(b);
        fxTable.getColumns().setAll(fxTitleCol, fxSeatsCol, fxAmountCol, fxDateCol, fxTimeCol, fxDurationCol,
                fxActivityCol, fxLocationCol, fxLanguageCol, fxMyRatingCol);

    }



    @FXML
    private void backButtonHandler(ActionEvent event) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("./View/dayTripSearchView.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

        DayTripController dayTripController = loader.getController();
        dayTripController.initData(customer);

    }

    @FXML
    private void logoutHandler(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("./View/dayTripSearchView.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

        DayTripController dayTripController = loader.getController();
        customer = null;
        dayTripController.initData(customer);

    }

    @FXML
    private void cancelBookingHandler() throws Exception{
        Booking booking = fxTable.getSelectionModel().getSelectedItem();
        if(booking == null) return;
        else{
            customer.removeBooking(booking);
            BookingDataConnection bookingDataConn = new BookingDataConnection();
            String removeBooking = "DELETE FROM BOOKINGS WHERE dayTripId = " + booking.getDayTripId() +
                    " AND customerId = " + customer.getCustomerId() + ";";
            bookingDataConn.insertBooking(removeBooking);
            ReviewDataConnection reviewDataConn = new ReviewDataConnection();
            String removeReview = "DELETE FROM reviews WHERE dayTripId = " + booking.getDayTripId() +
                    " AND customerId = " + customer.getCustomerId() + ";";
            reviewDataConn.updateReviews(removeReview);
            DayTripDataConnection dayTripDataConn = new DayTripDataConnection();
            String addTrips = "UPDATE dayTrips SET availableSeats = availableSeats + " + booking.getNumberOfGuests()
                    + " WHERE dayTripId = " + booking.getDayTripId() + ";";
            dayTripDataConn.updateTrip(addTrips);
            fxTable.getItems().clear();
            ObservableList<Booking> b = FXCollections.observableArrayList(customer.getBookings());
            fxTable.setItems(b);
        }

    }

    @FXML
    private void changeBookingHandler() throws Exception{
        Booking booking = fxTable.getSelectionModel().getSelectedItem();
        ChangeBooking changeBooking = new ChangeBooking(booking);
        changeBooking.changeBooking();
        fxTable.getItems().clear();
        ObservableList<Booking> b = FXCollections.observableArrayList(customer.getBookings());
        fxTable.setItems(b);

    }

    @FXML
    private void giveReviewHandler() throws Exception{
        Booking booking = fxTable.getSelectionModel().getSelectedItem();
        GiveReview giveReview = new GiveReview(booking);
        giveReview.giveReview();
        fxTable.getItems().clear();
        ObservableList<Booking> b = FXCollections.observableArrayList(customer.getBookings());
        fxTable.setItems(b);
    }


}
