package Data;


import Model.DayTrip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class DayTripDataConnection {
    private static final String SQL_PATH = "DayTourSearch-3D/src/Data" + File.separator + "schema.sql";
    private static final String DB_PATH = "DayTourSearch-3D/src/Data" + File.separator + "dataBases.db";
    private Connection connection;
    private Statement statement;
    private Scanner read;
    private String command;

    public DayTripDataConnection() throws IOException, ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        connection = null;
        statement = null;
        read = null;
        command = null;

        try {
            File sql = new File(SQL_PATH);
            if (!sql.exists()) {
                System.out.println("No schema found.");
                System.exit(0);
            }

            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            statement = connection.createStatement();

            /*
            read = new Scanner(sql);
            read.useDelimiter(";");

            while (read.hasNext()) {
                command = read.next();
                System.out.println(command);
                statement.execute(command);
            }
            read.close();

             */

        } catch (SQLException err) {
            System.err.println(err.getMessage());
        }

    }

    public ObservableList<DayTrip> getDayTrips() throws Exception{
        String query = "SELECT * FROM DAYTRIPS;";
        ResultSet rs = statement.executeQuery(query);
        ObservableList<DayTrip> trips = FXCollections.observableArrayList();
        DayTrip dayTrip;
        int dayTripId;
        String title;
        int price;
        int duration;
        LocalDate date;
        LocalTime startTime;
        int availableSeats;
        String language;
        String location;
        String activity;
        LocalDate dateAdded;
        String description;
        while(rs.next()){
            dayTripId = rs.getInt("dayTripId");
            title = rs.getString("title");
            price = rs.getInt("price");
            duration = rs.getInt("duration");
            date = LocalDate.parse(rs.getString("dateStart"));
            startTime = LocalTime.now(); //Þarf að fiffa
            availableSeats = rs.getInt("availableSeats");
            language = rs.getString("languageSpoken");
            location = rs.getString("location"); //Þarf að fiffa
            activity = rs.getString("activity");
            description = rs.getString("description");
            dateAdded = LocalDate.parse(rs.getString("dateadded"));
            DayTrip trip = new DayTrip(dayTripId, title, price, duration,date, startTime,  availableSeats,
                    language, location, activity, dateAdded, description);
            trips.add(trip);


        }

        return trips;
    }
}
