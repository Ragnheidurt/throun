package View;

import Model.DayTrip;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class DayTripInfo extends DialogPane {

    @FXML
    private Label fxTitle;
    @FXML
    private Text fxDescription;
    @FXML
    private ListView fxListReview;

    public DayTripInfo(DayTrip trip){
        // Read .fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/dayTripInfoView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this); // controller
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

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
