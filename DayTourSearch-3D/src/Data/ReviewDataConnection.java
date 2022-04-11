package Data;

import Model.Review;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class ReviewDataConnection {
    private static final String SQL_PATH = "DayTourSearch-3D/src/Data" + File.separator + "schema.sql";
    private static final String DB_PATH = "DayTourSearch-3D/src/Data" + File.separator + "dataBases.db";
    private Connection connection;
    private Statement statement;
    private Scanner read;
    private String command;

    public ReviewDataConnection() throws Exception{
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
        statement.close();
        connection.close();
    }

    public ObservableList<Review> getReviews(int dayTripId) throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
        statement = connection.createStatement();
        String query = "SELECT * FROM REVIEWS WHERE dayTripId = " + dayTripId + ";";
        ResultSet rs = statement.executeQuery(query);
        ObservableList<Review> reviews = FXCollections.observableArrayList();
        while (rs.next()) {
            Review review = new Review(rs.getInt("rating"),rs.getString("review"),
                    rs.getInt("customerId"),rs.getInt("dayTripId"));
            reviews.add(review);
        }
        statement.close();
        connection.close();
        return reviews;
    }

}
