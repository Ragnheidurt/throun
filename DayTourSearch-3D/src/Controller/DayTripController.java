package Controller;

import Data.BookingDataConnection;
import Data.CustomerDataConnection;
import Data.DayTripDataConnection;
import Model.Booking;
import Model.Customer;
import Model.DayTrip;
import View.DayTripInfo;
import View.UserLogin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.io.IOException;

public class DayTripController {

    // Interface attributes
    @FXML
    private DatePicker fxDate;
    @FXML
    private ComboBox<String> fxActivity;
    @FXML
    private ComboBox<String> fxLocation;
    @FXML
    private ComboBox<String> fxLanguage;
    @FXML
    private Label fxCustomer;
    @FXML
    private TableView<DayTrip> fxTable;
    @FXML
    private TableColumn<DayTrip, String> fxTitleCol;
    @FXML
    private TableColumn<DayTrip, Integer> fxSeatsCol;
    @FXML
    private TableColumn<DayTrip, String> fxDateCol;
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



    // Data attributes
    private ObservableList<DayTrip> dayTrips;
    private DayTripDataConnection dayTripConn;
    private CustomerDataConnection customerConn;
    private Customer customer;
    private BookingDataConnection bookingDataConn;
    private ObservableList<Booking> bookings;





    /**
     * Interface initialized and day trip data added to table.
     */
    @FXML
    public void initialize() throws Exception{
        // Get the user info from login system
        // Check if the user has an account, if not then we exit
        UserLogin login = new UserLogin();
        Pair<Integer, String> user =  login.getUser();
        // FÁ TENGINGU VIÐ CUTOMERDATACONNECTION OG ATHUGA HVORT ÞESSI USER ER Í GAGNAGRUNNINUM
        // EF SVO ER ÞÁ BÚUM VIÐ TIL USER HLUT, ANNARS HÆTTUM VIÐ

        customerConn = new CustomerDataConnection();
        customer = customerConn.getCustomer(user.getKey(),user.getValue());
        fxCustomer.setText(customer.getUsername());

        // FÁ TENGINGU VIÐ BOOKINGDATACONNECTION OG BÆTA VIÐ FERÐUM ÞESSA CUSTOMERS

        bookingDataConn = new BookingDataConnection();
        bookings = bookingDataConn.getBookings(customer.getCustomerId());
        for(Booking booking : bookings) customer.addBooking(booking);

        
        // Items to combobox
        fxActivity.setItems(FXCollections.observableArrayList("", "Fjallganga", "Sigling", "Skíði", "Köfun"));
        fxLocation.setItems(FXCollections.observableArrayList("", "S", "V", "N", "A"));
        fxLanguage.setItems(FXCollections.observableArrayList("", "íslenska", "enska"));

        // Get all day trips
        dayTripConn = new DayTripDataConnection();
        dayTrips = dayTripConn.getDayTrips();

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


    @FXML
    private void tripInfoHandler(ActionEvent event) throws IOException {
        // Get the day trip that is selected and create a new DayTripInfo object for this trip
        DayTrip trip = fxTable.getSelectionModel().getSelectedItem();   // kannski betra að nota id???
        if(trip == null) return;
        else{
            DayTripInfo info = new DayTripInfo(trip);

        }





        // KANNSKI BETRA AÐ HAFA DIALOG HELDUR EN ALVEG NÝJA SENU??
        // ÞURFUM AMK AÐ GETA KOMIST TIL BAKA AFTUR Í DAYTRIPSEARCHVIEW.FXML

        /*
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("View/dayTripInfoView.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

        DayTripInfoController tourController = loader.getController();
        tourController.initData(trip);

         */




    }

    @FXML
    private void bookTripHandler(ActionEvent event) throws IOException {
        System.out.println("Book");

    }



    @FXML
    private void myTripsHandler(ActionEvent event) throws IOException {
        System.out.println("My Trips");

    }

    @FXML
    private void searchHandler(ActionEvent event) throws IOException {
        System.out.println("Search");

    }

    @FXML
    private void logoutHandler(ActionEvent event) throws IOException {
        System.out.println("Logout");

    }



}
