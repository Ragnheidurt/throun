package Data;

import Model.Customer;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class CustomerDataConnection {
    private static final String SQL_PATH = "DayTourSearch-3D/src/Data" + File.separator + "schema.sql";
    private static final String DB_PATH = "DayTourSearch-3D/src/Data" + File.separator + "dataBases.db";
    private Connection connection;
    private Statement statement;
    private Scanner read;
    private String command;

    public CustomerDataConnection() throws Exception{
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

    /**
     * @param username
     * @param password
     * @return - Customer in db if both username and password are a match, otherwise null
     * @throws Exception
     */
    public Customer getCustomer(String username, String password) throws Exception{
        connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
        statement = connection.createStatement();
        String query = "SELECT * FROM CUSTOMERS WHERE username = '" + username + "';";
        ResultSet rs = statement.executeQuery(query);
        // If username is not valid
        if(!rs.next()) {
            System.exit(0);
            //return null;
        }
        String result = rs.getString("password");
        // If password is not valid
        if(!password.equals(result)){
            System.exit(0);
            //return null;
        }
        int customerId = rs.getInt("customerId");
        statement.close();
        connection.close();
        return new Customer(customerId,password,username);
    }

}
