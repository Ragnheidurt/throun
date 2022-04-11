package Data;

import Model.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class BookingDataConnection {
    private static final String SQL_PATH = "DayTourSearch-3D/src/Data" + File.separator + "schema.sql";
    private static final String DB_PATH = "DayTourSearch-3D/src/Data" + File.separator + "dataBases.db";
    private Connection connection;
    private Statement statement;
    private Scanner read;
    private String command;

    public BookingDataConnection() throws IOException, ClassNotFoundException{
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

        } catch (SQLException err) {
            System.err.println(err.getMessage());
        }
    }

    public ObservableList<Booking> getBookings(int customerId) throws Exception{
        String query = "SELECT * FROM BOOKINGS WHERE customerId = " + customerId + ";";
        ResultSet rs = statement.executeQuery(query);
        ObservableList<Booking> bookings = FXCollections.observableArrayList();
        while (rs.next()){
            Booking booking = new Booking(rs.getInt("customerId"),
                    rs.getInt("dayTripId"),rs.getInt("numberOfGuests"));
            bookings.add(booking);
        }
        return bookings;
    }

    public void insertBooking(String insert) throws Exception{
        statement.executeUpdate(insert);
    }

}
