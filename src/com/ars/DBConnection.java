package com.ars;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn != null) {
            return conn;
        }

        try {
            // Load properties from external file
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("db_config.properties");
            props.load(fis);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            // Register MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database Connected Successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver Not Found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Database Connection Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error loading DB config: " + e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
