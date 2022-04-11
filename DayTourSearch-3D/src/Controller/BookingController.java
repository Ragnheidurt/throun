package Controller;

import Model.Booking;
import Model.Customer;
import Model.DayTrip;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Pair;

import java.awt.print.Book;
import java.io.IOException;
import java.util.Optional;

public class BookingController extends DialogPane {

    // Interface attributes
    @FXML
    private Label fxTitle, fxAvailableSeats, fxPrice, fxAmount;
    @FXML
    private ComboBox<Integer> fxNumberOfGuests;

    private DayTrip trip;
    private Customer customer;

    public BookingController(DayTrip trip, Customer customer){
        this.trip = trip;
        this.customer = customer;

        // Read .fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/bookingView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this); // controller
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        fxTitle.setText(trip.getTitle());
        fxAvailableSeats.setText(String.valueOf(trip.getAvailableSeats()));
        fxPrice.setText(String.valueOf(trip.getPrice()));

        for(int i = 1; i <= trip.getAvailableSeats(); i++){
            fxNumberOfGuests.getItems().add(i);
        }
        fxNumberOfGuests.setValue(1);
    }

    @FXML
    private void updateAmount(){
        fxAmount.setText(String.valueOf(fxNumberOfGuests.getValue()* trip.getPrice()));
    }

    public Booking getBooking(){
        // Dialogurinn (umgjörðin um DialogPane) búin til
        Dialog<Booking> d = new Dialog<>();

        // Innihaldið sett í dialog-inn umgjörðina
        d.setDialogPane(this);

        // Sett regla um hvenær í lagi hnappur er virkur
        //iLagiRegla(lookupButton(fxILagi));

        // Búum til hlut af nýjum nafnlausum innri klasa sem útfærir interface
        // Callback fyrir klasana ButtonType og Vidburdur
        // Callback hefur eina aðferð og við endurforritum hana
        d.setResultConverter(b -> {                                 // b er af taginu ButtonType
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return new Booking(customer.getCustomerId(), trip.getDayTripId(), fxNumberOfGuests.getValue());
            } else {
                return null;
            }
        });

        // Dialog birtur og svarið fengið
        Optional<Booking> utkoma = d.showAndWait();
        return utkoma.orElse(null);


    }




}
