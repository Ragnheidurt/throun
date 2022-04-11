package Data;

import Model.Customer;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class CustomerDataConnection {
    private static final String SQL_PATH = "DayTourSearch-3D/src/Data" + File.separator + "schema.sql";
    private static final String DB_PATH = "DayTourSearch-3D/src/Data" + File.separator + "dataBases.db";
    private Connection connection;
    private Statement statement;
    private Scanner read;
    private String command;

    public CustomerDataConnection() throws IOException, ClassNotFoundException{
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

    public Customer getCustomer(String username, String password) throws Exception{
        String query = "SELECT * FROM CUSTOMERS WHERE username = '" + username + "';";
        ResultSet rs = statement.executeQuery(query);
        if(!rs.next()) {
            System.exit(0);
        }
        String result = rs.getString("password");
        if(!password.equals(result)) System.exit(0); //Fiffa

        int customerId = rs.getInt("customerId");

        return new Customer(customerId,password,username);
    }

    public static void main(String[] args) {

    }
}
