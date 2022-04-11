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
    private TableView fxTable;
    @FXML
    private TableColumn<DayTrip, String> fxTitleCol;
    @FXML
    private TableColumn<Booking, Integer> fxSeatsCol;
    @FXML
    private TableColumn<DayTrip, String> fxDateCol;
    /*
    @FXML
    private TableColumn<DayTrip, Integer> fxDurationCol;
    @FXML
    private TableColumn<DayTrip, Integer> fxPriceCol;
    @FXML
    private TableColumn<DayTrip, String> fxActivityCol;
    @FXML
    private TableColumn<DayTrip, String> fxLocationCol;
    @FXML
    private TableColumn<DayTrip, String> fxLanguageCol;
    @FXML
    private TableColumn<DayTrip, Integer> fxRatingCol;
    @FXML
    private TableColumn<DayTrip, String> fxDateAddedCol;

     */

    // Data attributes
    private ObservableList<DayTrip> customerDayTrips = FXCollections.observableArrayList();
    private Customer customer;
    private ObservableList<DayTrip> dayTrips;

    public CustomerController(){
        System.out.println("Hér");
    }

    public void initData(Customer customer, ObservableList<DayTrip> dayTrips){
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

        ObservableList<Booking> b = FXCollections.observableArrayList(bookings);

        // Add to table
        fxTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        fxSeatsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfGuests"));
        fxDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        /*
        fxDurationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        fxPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        fxActivityCol.setCellValueFactory(new PropertyValueFactory<>("activity"));
        fxLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        fxLanguageCol.setCellValueFactory(new PropertyValueFactory<>("language"));
        fxRatingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        fxDateAddedCol.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));

         */
        fxTable.setItems(b);
        fxTable.getColumns().setAll(fxTitleCol, fxSeatsCol, fxDateCol);
        //fxTable.getColumns().setAll(fxTitleCol, fxSeatsCol, fxDateCol, fxDurationCol, fxPriceCol, fxActivityCol,
          //      fxLocationCol, fxLanguageCol, fxRatingCol, fxDateAddedCol);

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
