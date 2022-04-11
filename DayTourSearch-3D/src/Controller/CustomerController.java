package Controller;

import Model.Booking;
import Model.Customer;
import Model.DayTrip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class CustomerController {

    // Interface attributes
    @FXML
    private Label fxCutomer;
    @FXML
    private TableView fxTable;
    @FXML
    private TableColumn<DayTrip, String> fxTitleCol;
    @FXML
    private TableColumn<Booking, Integer> fxSeatsCol;
    @FXML
    private TableColumn<DayTrip, Integer> fxAmountCol;
    @FXML
    private TableColumn<DayTrip, String> fxDateCol;
    @FXML
    private TableColumn<DayTrip, String> fxTimeCol;
    @FXML
    private TableColumn<DayTrip, Integer> fxDurationCol;
    @FXML
    private TableColumn<DayTrip, String> fxActivityCol;
    @FXML
    private TableColumn<DayTrip, String> fxLocationCol;
    @FXML
    private TableColumn<DayTrip, String> fxLanguageCol;
    @FXML
    private TableColumn<DayTrip, Integer> fxRatingCol;

    // Data attributes
    private ObservableList<DayTrip> customerDayTrips = FXCollections.observableArrayList();


    public CustomerController(Customer customer, ObservableList<DayTrip> dayTrips){
        //
        ArrayList<Booking> bookings = customer.getBookings();
        for(DayTrip d : dayTrips){
            for(Booking b : bookings){
                if(d.getDayTripId() == b.getDayTripId()) customerDayTrips.add(d);
            }
        }

        // Add to table
        fxTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        fxSeatsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfGuests"));
        fxAmountCol.setCellValueFactory(new PropertyValueFactory<>("price"));  // SKOÐA!!!!!!
        fxDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        fxTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        fxDurationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        fxActivityCol.setCellValueFactory(new PropertyValueFactory<>("activity"));
        fxLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        fxLanguageCol.setCellValueFactory(new PropertyValueFactory<>("language"));
        fxRatingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        fxTable.setItems(customerDayTrips);
        fxTable.getColumns().setAll(fxTitleCol, fxSeatsCol, fxAmountCol, fxDateCol, fxTimeCol, fxDurationCol, fxActivityCol,
                fxLocationCol, fxLanguageCol, fxRatingCol);


    }


}
