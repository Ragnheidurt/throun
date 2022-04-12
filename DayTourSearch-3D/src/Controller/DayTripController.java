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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private BookingController bookingController;
    private Boolean loggedIn = false;





    /**
     * Interface initialized and day trip data added to table.
     */
    @FXML
    public void initialize() throws Exception{
        // Get the user info from login system
        // Check if the user has an account, if not then we exit
        Pair<String, String> user = null;
        if(!loggedIn) {
            UserLogin login = new UserLogin();
            user = login.getUser();
            loggedIn = true;
        }

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

        fxActivity.setValue("");
        fxLocation.setValue("");
        fxLanguage.setValue("");

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
    private void tripInfoHandler(ActionEvent event) throws Exception {
        // Get the day trip that is selected and create a new DayTripInfo object for this trip
        DayTrip trip = fxTable.getSelectionModel().getSelectedItem();   // kannski betra að nota id???
        if(trip == null) return;
        else{
            DayTripInfo info = new DayTripInfo(trip);
        }





        // KANNSKI BETRA AÐ HAFA DIALOG HELDUR EN ALVEG NÝJA SENU??
        // ÞURFUM AMK AÐ GETA KOMIST TIL BAKA AFTUR Í DAYTRIPSEARCHVIEW.FXML

    }

    @FXML
    private void bookTripHandler(ActionEvent event) throws Exception {
        System.out.println("Book");
        DayTrip trip = fxTable.getSelectionModel().getSelectedItem();

        bookingController = new BookingController(trip,customer);
        Booking booking = bookingController.getBooking();
        if(booking == null) return;

        String update = "UPDATE dayTrips SET availableSeats = availableSeats - " + booking.getNumberOfGuests()
            + " WHERE dayTripId = " + trip.getDayTripId() + ";";
        dayTripConn.updateTrip(update);

        Boolean hasBooked = false;
        Booking oldBooking = new Booking(0,0,0,"",0,0, LocalDate.now(), LocalTime.now(),"","","","");
        for(Booking b : customer.getBookings()){
            if(b.getDayTripId() == booking.getDayTripId()){
                hasBooked = true;
                oldBooking = b;
            }
        }

        if(hasBooked){
            oldBooking.setNumberOfGuests(oldBooking.getNumberOfGuests() + booking.getNumberOfGuests());
            String updateBooking = "UPDATE bookings SET numberOfGuests = " + oldBooking.getNumberOfGuests()
                    + " WHERE dayTripId = " + oldBooking.getDayTripId() + ";";
            bookingDataConn.insertBooking(updateBooking);
        }
        else{
            String insertBooking = "INSERT INTO BOOKINGS VALUES(" + customer.getCustomerId() + ","
                    + trip.getDayTripId() + "," + booking.getNumberOfGuests() + ");";
            bookingDataConn.insertBooking(insertBooking);
            customer.addBooking(booking);
        }
        searchHandler(null);


    }



    @FXML
    private void myTripsHandler(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("./View/myTripsView.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

        CustomerController customerController = loader.getController();
        customerController.initData(customer);

    }

    @FXML
    private void searchHandler(ActionEvent event) throws Exception {
        String date = fxDate.getValue() == null ? "" : fxDate.getValue().toString();
        String activity = fxActivity.getValue();
        String location = fxLocation.getValue();
        String language = fxLanguage.getValue();

        String query = "SELECT * FROM DAYTRIPS WHERE dateStart ";
        query += date.equals("") ? "Like '%'" : ("= '" + date + "'");
        query += " AND activity ";
        query += activity.equals("") ? "Like '%'" : ("= '" + activity + "'");
        query += " AND location ";
        query += location.equals("") ? "Like '%'" : ("= '" + location + "'");
        query += " AND languageSpoken ";
        query += language.equals("") ? "Like '%'" : ("= '" + language + "'");
        query += ";";

        System.out.println(query);

        ObservableList<DayTrip> filteredTrips = dayTripConn.filterDayTrips(query);
        for(DayTrip trip : filteredTrips) System.out.println(trip.getTitle() + " " + trip.getRating());
        fxTable.getItems().clear();
        fxTable.setItems(filteredTrips);
        fxTable.getColumns().setAll(fxTitleCol, fxSeatsCol, fxDateCol, fxDurationCol, fxPriceCol, fxActivityCol,
                fxLocationCol, fxLanguageCol, fxRatingCol, fxDateAddedCol);

    }

    @FXML
    private void logoutHandler(ActionEvent event) throws IOException {
        System.out.println("Logout");

    }



}
