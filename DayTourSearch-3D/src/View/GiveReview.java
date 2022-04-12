package View;

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

public class GiveReview extends DialogPane {

    @FXML
    private Label fxTitle;
    @FXML
    private Text fxDescription;
    @FXML
    private ComboBox<Double> fxRating;
    @FXML
    private TextArea fxReview;

    private ReviewDataConnection reviewDataConn;
    private ObservableList<Review> reviews;
    private Booking booking;
    private DayTrip dayTrip;




    public GiveReview(Booking booking) throws Exception{
        this.booking = booking;
        // Read .fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/giveReviewView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this); // controller
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Set attributes
        //DayTripDataConnection dayTripDataConn = new DayTripDataConnection();
        //dayTrip = dayTripDataConn.getDayTrip(booking.getDayTripId());
        fxTitle.setText(booking.getTitle());
        fxDescription.setText(booking.getDescription());
        for(int i = 1; i<= 5; i++) fxRating.getItems().add(i*1.0);
        if(booking.getRating()!=-1) {
            fxRating.setValue(booking.getRating());
        }
        else {
            fxRating.setValue(3.0);
        }
        fxReview.setText(booking.getReview());
    }


    public void giveReview() throws Exception{
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

           ReviewDataConnection reviewDataConn = new ReviewDataConnection();
           if(booking.getRating() == -1){
               booking.setReview(fxReview.getText());
               booking.setRating(fxRating.getValue());
               reviewDataConn.updateReviews("INSERT INTO reviews VALUES(" + booking.getCustomerId() + "," +
                       booking.getDayTripId() + "," + booking.getRating() + ",'" +
                       booking.getReview() + "');");

           }
           else{
               booking.setReview(fxReview.getText());
               booking.setRating(fxRating.getValue());
               reviewDataConn.updateReviews("UPDATE reviews SET rating = " + booking.getRating() +
                       " AND review = '" + booking.getReview() + "' WHERE dayTripId = " +
                       booking.getDayTripId() + " AND customerId = " + booking.getCustomerId() + ";");
           }


        }

    }





}
