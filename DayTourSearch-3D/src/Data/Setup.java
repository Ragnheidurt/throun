package Data;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Script to set up the required files on the system.
 *
 * @author: Einar Jónsson, Eydís Sylvía Einarsdóttir, Jaan Jaerving, Snorri
 * Steinn Stefánsson Thors
 */
public class Setup {
    private static final String SQL_PATH = "DayTourSearch-3D/src/Data" + File.separator + "schema.sql";
    private static final String DB_PATH = "DayTourSearch-3D/src/Data" + File.separator + "dataBases.db";

    /**
     * Helper function that creates a database file if it does not yet exist.
     *
     * @throws IOException
     */
    private static Boolean initDB() throws IOException {
        Boolean exists = false;
        try {
            File db = new File(DB_PATH);
            if (db.createNewFile())
                System.out.println("Database created.");
            else {
                System.out.println("Database already exists.");
                exists = true;
            }
        } catch (Exception err) {
            System.err.println(err);
        }
        return exists;
    }

    private static void dropTable() throws Exception{

    }

    /**
     * Will attempt to create and populate a database in the current package
     * directory if it does not already exist.
     *
     * In order to not overwrite information that already exists the program will
     * exit if any existing database or tables are detected.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void setup() throws IOException, ClassNotFoundException {
        Boolean exists = initDB();

        Class.forName("org.sqlite.JDBC");

        Connection connection = null;
        Statement statement = null;
        Scanner read = null;
        String command = null;


        try {
            File sql = new File(SQL_PATH);
            if (!sql.exists()) {
                System.out.println("No schema found.");
                System.exit(0);
            }

            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            statement = connection.createStatement();

            if(exists){
                statement.executeUpdate("DROP TABLE DAYTRIPS;");
                statement.executeUpdate("DROP TABLE CUSTOMERS;");
                statement.executeUpdate("DROP TABLE REVIEWS;");
                statement.executeUpdate("DROP TABLE BOOKINGS;");
            }

            read = new Scanner(sql);
            read.useDelimiter(";");

            while (read.hasNext()) {
                command = read.next();
                System.out.println(command);
                statement.execute(command);
            }
            read.close();

        } catch (SQLException err) {
            System.err.println(err.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException err) {
                System.err.println(err);
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        setup();
    }
}
