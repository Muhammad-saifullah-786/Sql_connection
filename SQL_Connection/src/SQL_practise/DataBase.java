package SQL_practise;
  // Correct for Connection
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    // Database URL, Username, and Password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "Saifullah@786";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to MySQL...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Successfully connected to MySQL!");

            // Step 3: Create a statement
            statement = connection.createStatement();

            // Step 4: Create the 'practice' database if it does not exist
            String createDatabase = "CREATE DATABASE IF NOT EXISTS practice;";
            statement.executeUpdate(createDatabase);
            System.out.println("Database 'practice' created successfully.");

            // Step 5: Switch to the 'practice' database
            String useDatabase = "USE practice;";
            statement.executeUpdate(useDatabase);

            // Step 6: Create the Country table
            String createCountryTable = "CREATE TABLE IF NOT EXISTS Country ("
                    + "id INT PRIMARY KEY, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "population INT, "
                    + "area INT"
                    + ");";
            statement.executeUpdate(createCountryTable);
            System.out.println("Country table created successfully.");

            // Step 7: Create the City table
            String createCityTable = "CREATE TABLE IF NOT EXISTS City ("
                    + "id INT PRIMARY KEY, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "country_id INT, "
                    + "population INT, "
                    + "rating INT, "
                    + "FOREIGN KEY (country_id) REFERENCES Country(id)"
                    + ");";
            statement.executeUpdate(createCityTable);
            System.out.println("City table created successfully.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Step 8: Close resources
            try {
                if (statement != null) {
                    statement.close(); // Close Statement
                }
                if (connection != null) {
                    connection.close(); // Close Connection
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}