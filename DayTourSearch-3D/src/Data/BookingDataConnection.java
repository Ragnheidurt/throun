package Data;

import Model.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDataConnection {
    private static final String SQL_PATH = "DayTourSearch-3D/src/Data" + File.separator + "schema.sql";
    private static final String DB_PATH = "DayTourSearch-3D/src/Data" + File.separator + "dataBases.db";
    private Connection connection;
    private Statement statement;

    public BookingDataConnection() throws Exception{
        Class.forName("org.sqlite.JDBC");
        connection = null;
        statement = null;

        try {
            File sql = new File(SQL_PATH);
            if (!sql.exists()) {
                System.out.println("No schema found.");
                System.exit(0);
            }

            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            statement = connection.createStatement();

        } catch (SQLException err) {
            System.err.println(err.getMessage());
        }
        statement.close();
        connection.close();
    }

    public ObservableList<Booking> getBookings(int customerId) throws Exception{
        connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
        statement = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();
        String query = "SELECT * FROM BOOKINGS WHERE customerId = " + customerId + ";";
        ResultSet rs = statement.executeQuery(query);
        ObservableList<Booking> bookings = FXCollections.observableArrayList();
        int dayTripId; int numberOfGuests; String title; int amount; int duration; String description;
        double myRating; LocalDate date; LocalTime startTime; String language; String location; String activity;
        String myReview;
        ResultSet dayTripQuery;
        ResultSet reviewQuery;
        while (rs.next()){
            // Get booking attributes from db
            dayTripId = rs.getInt("dayTripId");
            numberOfGuests = rs.getInt("numberOfGuests");
            dayTripQuery = statement2.executeQuery("SELECT * FROM daytrips WHERE dayTripId = " + dayTripId + ";");
            title = dayTripQuery.getString("title");
            amount = numberOfGuests*dayTripQuery.getInt("price");
            duration = dayTripQuery.getInt("duration");
            description = dayTripQuery.getString("description");
            date = LocalDate.parse(dayTripQuery.getString("dateStart"));
            startTime = LocalTime.parse(dayTripQuery.getString("startTime"));
            language = dayTripQuery.getString("languageSpoken");
            location = dayTripQuery.getString("location");
            activity = dayTripQuery.getString("activity");

            // Create a booking instance with attributes from db
            Booking booking = new Booking(customerId,dayTripId,numberOfGuests,title,amount,
                    duration,date,startTime,language,location,activity,description);

            // If booking has a review then set it to booking instance
            reviewQuery = statement3.executeQuery("SELECT * FROM reviews WHERE customerId = " + customerId +
                    " AND dayTripId = " + dayTripId + ";");
            if(reviewQuery.next()) {
                myRating = reviewQuery.getInt("rating");
                myReview = reviewQuery.getString("review");
                booking.setRating(myRating);
                booking.setReview(myReview);
            }

            bookings.add(booking);
        }

        statement.close();
        statement2.close();
        statement3.close();
        connection.close();

        return bookings;
    }

    public void updateBooking(String insert) throws Exception{
        connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
        statement = connection.createStatement();
        statement.executeUpdate(insert);
        statement.close();
        connection.close();
    }

}
