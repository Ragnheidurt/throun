package View;

import Data.BookingDataConnection;
import Data.DayTripDataConnection;
import Data.ReviewDataConnection;
import Model.Booking;
import Model.DayTrip;
import Model.Review;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Optional;

public class ChangeBooking extends DialogPane {

    @FXML
    private Label fxTitle;
    @FXML
    private Text fxDescription;
    @FXML
    private ComboBox<Integer> fxNumberOfGuests;
    @FXML
    private Label fxAmount;

    private ReviewDataConnection reviewDataConn;
    private ObservableList<Review> reviews;
    private Booking booking;
    private DayTrip dayTrip;




    public ChangeBooking(Booking booking) throws Exception{
        this.booking = booking;
        // Read .fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/changeBookingView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this); // controller
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Set attributes
        DayTripDataConnection dayTripDataConn = new DayTripDataConnection();
        dayTrip = dayTripDataConn.getDayTrip(booking.getDayTripId());
        fxTitle.setText(dayTrip.getTitle());
        fxDescription.setText(dayTrip.getDescription());
        for(int i = 1; i<= dayTrip.getAvailableSeats(); i++) fxNumberOfGuests.getItems().add(i);
        fxNumberOfGuests.setValue(booking.getNumberOfGuests());
        fxAmount.setText(String.valueOf(dayTrip.getPrice()*fxNumberOfGuests.getValue()) + " kr");

    }

    @FXML
    private void updateAmount(){
        fxAmount.setText(String.valueOf(fxNumberOfGuests.getValue()* dayTrip.getPrice()) + " kr");
    }

    public void changeBooking() throws Exception{
        // Dialogurinn (umgjörðin um DialogPane) búin til
        Dialog<ButtonType> d = new Dialog<>();

        // Innihaldið sett í dialog-inn umgjörðina
        d.setDialogPane(this);

        // Sett regla um hvenær í lagi hnappur er virkur
        //iLagiRegla(lookupButton(fxILagi));

        // Búum til hlut af nýjum nafnlausum innri klasa sem útfærir interface
        // Callback fyrir klasana ButtonType og Vidburdur
        // Callback hefur eina aðferð og við endurforritum hana
        Optional<ButtonType> utkoma = d.showAndWait();
        if(utkoma.isPresent() && (utkoma.get().getButtonData() == ButtonBar.ButtonData.OK_DONE)){
            int deltaSeats = fxNumberOfGuests.getValue() - booking.getNumberOfGuests();
            booking.setNumberOfGuests(fxNumberOfGuests.getValue());
            BookingDataConnection bookingDataConn = new BookingDataConnection();
            bookingDataConn.insertBooking("UPDATE bookings SET numberOfGuests = " + booking.getNumberOfGuests() +
                    " WHERE customerId = " + booking.getCustomerId() + " AND dayTripId = " +
                    booking.getDayTripId() + ";");
            DayTripDataConnection dayTripDataConn = new DayTripDataConnection();

            dayTripDataConn.updateTrip("UPDATE dayTrips SET availableSeats = availableSeats - " +
                    deltaSeats + " WHERE dayTripId = " + booking.getDayTripId() + ";");
        }

    }





}
