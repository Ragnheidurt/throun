package Controller;

import Model.Booking;
import Model.Customer;
import Model.DayTrip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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
    private ObservableList<DayTrip> customerDayTrips = FXCollections.observableArrayList();
    private Customer customer;
    private ObservableList<DayTrip> dayTrips;


    public void initData(Customer customer){
        fxCustomer.setText(customer.getUsername());

        // Get this cutomers bookings
        ArrayList<Booking> bookings = customer.getBookings();
        ObservableList<Booking> b = FXCollections.observableArrayList(bookings);

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
        fxTable.setItems(b);
        fxTable.getColumns().setAll(fxTitleCol, fxSeatsCol, fxAmountCol, fxDateCol, fxTimeCol, fxDurationCol,
                fxActivityCol, fxLocationCol, fxLanguageCol, fxMyRatingCol);

    }
    /*
    public CustomerController(Customer customer, ObservableList<DayTrip> dayTrips) throws Exception{
        this.customer = customer;
        this.dayTrips = dayTrips;
    }

     */

    /*
    public CustomerController(Customer customer, ObservableList<DayTrip> dayTrips) throws Exception{

        fxCustomer.setText(customer.getUsername());

        System.out.println(customer.getUsername());


        //
        ArrayList<Booking> bookings = customer.getBookings();
        for(DayTrip d : dayTrips){
            for(Booking b : bookings){
                if(d.getDayTripId() == b.getDayTripId()) customerDayTrips.add(d);
            }
        }

        System.out.println(customerDayTrips.get(0).getTitle());

        // Add to table
        fxTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        fxSeatsCol.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));
        fxDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        fxDurationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        fxPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        fxActivityCol.setCellValueFactory(new PropertyValueFactory<>("activity"));
        fxLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        fxLanguageCol.setCellValueFactory(new PropertyValueFactory<>("language"));
        fxRatingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        fxDateAddedCol.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
        fxTable.setItems(dayTrips);
        fxTable.getColumns().setAll(fxTitleCol, fxSeatsCol, fxDateCol, fxDurationCol, fxPriceCol, fxActivityCol,
                fxLocationCol, fxLanguageCol, fxRatingCol, fxDateAddedCol);


    }

     */



    @FXML
    private void backButtonHandler(){

    }

    @FXML
    private void logoutHandler(){

    }

    @FXML
    private void cancelBookingHandler(){

    }

    @FXML
    private void changeBookingHandler(){

    }

    @FXML
    private void giveReviewHandler(){

    }


}
