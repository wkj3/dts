package login;

import java.sql.*;

public class DBConnect {
    private Connection conn;

    public DBConnect() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            String url = "jdbc:mysql://localhost:3306"; //Change port if needed
            String user = "root"; //Change to user of database
            String password = "password"; //Change to password of database
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public boolean authenticateUser(String username, String password) {
        try {
            // Create a statement
            Statement stmt = conn.createStatement();

            // Execute a query to retrieve the user's information from the database
            String query = "SELECT * FROM userdata.login WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(query);

            // Check if the user exists in the database
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return false;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}