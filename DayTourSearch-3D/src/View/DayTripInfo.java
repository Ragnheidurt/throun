package View;

import Data.ReviewDataConnection;
import Model.DayTrip;
import Model.Review;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;

public class DayTripInfo extends DialogPane {

    @FXML
    private Label fxTitle;
    @FXML
    private Text fxDescription;
    @FXML
    private ListView fxListReview;
    @FXML
    private Label fxAverageRating;
    private ReviewDataConnection reviewDataConn;
    private ObservableList<Review> reviews;



    public DayTripInfo(DayTrip trip) throws Exception{
        // Read .fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/dayTripInfoView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this); // controller
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        //
        reviewDataConn = new ReviewDataConnection();
        reviews = reviewDataConn.getReviews(trip.getDayTripId());
        for(Review review : reviews){
            fxListReview.getItems().add(review.getRating() + "*. " + review.getReview());
        }
        fxAverageRating.setText(String.valueOf(trip.getRating()));


        // Dialogurinn (umgjörðin um DialogPane) búin til
        Dialog<DayTrip> d = new Dialog<>();
        // Innihaldið sett í dialog-inn umgjörðina
        d.setDialogPane(this);
        // Ósýnilegur takki
        d.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = d.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        // Dialog birtur og svarið fengið
        d.show();


        // Set attributes
        fxTitle.setText(trip.getTitle());
        fxDescription.setText(trip.getDescription());







    }





}
