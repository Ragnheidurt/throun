package Controller;

import Data.DayTripDataConnection;
import Model.DayTrip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class DayTripController implements Initializable {

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
    private Button fxSearch;
    @FXML
    private Button fxMyTrips;
    @FXML
    private Button fxBookTrip;
    @FXML
    private Button fxTripInfo;
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
    public ObservableList<DayTrip> dayTrips = FXCollections.observableArrayList();
    private DayTripDataConnection conn;


    /**
     * Day trip data added to table.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get all day trips (NÁUM ÚR GAGNAGRUNNI SEINNA MEIR)
        // conn.getDayTrips();
        dayTrips.add(new DayTrip(1,"Eyjafjallajökull",10000,7,3.5,LocalDate.parse("2021-06-22"),LocalTime.now(), 10,"íslenska",'S',"Fjallganga"));
        dayTrips.add(new DayTrip(2,"Sund",2000,2,4,LocalDate.parse("2021-06-22"),LocalTime.now(), 100,"íslenska",'S',"Sund"));

        // BÆTTI VIÐ DOUBLE RATING SEM TILVIKSBREYTU AMK TÍMABUNDIÐ FYRIR TÖFLUNA (BREYTTI EKKI GAGNAGRUNNINUM)

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
    private void tripInfoHandler(ActionEvent event) throws IOException {
        System.out.println("Hér");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("./View/dayTripInfoView.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


}
