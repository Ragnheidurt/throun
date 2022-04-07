package Controller;

import Model.DayTrip;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class DayTripInfoController extends DialogPane {

    @FXML
    private Label fxTitle, fxDescription;
    @FXML
    private ImageView fxImage;

    public DayTripInfoController(DayTrip trip){
        // Read .fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/dayTripInfoView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this); // controller
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Set attributes
        fxTitle.setText(trip.getTitle());
        fxDescription.setText(trip.getDescription());

        System.out.println("Hér");





    }




    public void initData(DayTrip trip){
        fxTitle.setText(trip.getTitle());
    }

}
