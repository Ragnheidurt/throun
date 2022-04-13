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

        // Set attributes of day trip to interface
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
        // Update amount label when number of guests is chosen
        fxAmount.setText(String.valueOf(fxNumberOfGuests.getValue()* dayTrip.getPrice()) + " kr");
    }

    public void changeBooking() throws Exception{
        Dialog<ButtonType> d = new Dialog<>();
        d.setDialogPane(this);
        Optional<ButtonType> utkoma = d.showAndWait();

        // If confirm button is pressed then
        if(utkoma.isPresent() && (utkoma.get().getButtonData() == ButtonBar.ButtonData.OK_DONE)){
            // Calculate change in number of seats of this booking and old booking
            int deltaSeats = fxNumberOfGuests.getValue() - booking.getNumberOfGuests();

            // Update booking and db
            booking.setNumberOfGuests(fxNumberOfGuests.getValue());
            BookingDataConnection bookingDataConn = new BookingDataConnection();
            bookingDataConn.insertBooking("UPDATE bookings SET numberOfGuests = " + booking.getNumberOfGuests() +
                    " WHERE customerId = " + booking.getCustomerId() + " AND dayTripId = " +
                    booking.getDayTripId() + ";");

            // Update avaiable seats in db
            DayTripDataConnection dayTripDataConn = new DayTripDataConnection();
            dayTripDataConn.updateTrip("UPDATE dayTrips SET availableSeats = availableSeats - " +
                    deltaSeats + " WHERE dayTripId = " + booking.getDayTripId() + ";");
        }

    }





}
