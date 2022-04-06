package Controller;

import Model.DayTrip;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;

public class DayTripInfoController {

    @FXML
    private Label fxTitle;



    public void initData(DayTrip trip){
        fxTitle.setText(trip.getTitle());
    }

}
