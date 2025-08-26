package com.ars;

import java.sql.*;
import java.util.Scanner;

public class LoginUser {

    // Console-friendly static method
        // ...existing code...
        private static int loggedInUserId = -1; // Add this field
    
        public static int getLoggedInUserId() {
            return loggedInUserId;
        }
    
        public static boolean login(Scanner sc) {
            System.out.print("Enter Email: ");
            String email = sc.nextLine().trim();
    
            System.out.print("Enter Password: ");
            String password = sc.nextLine().trim();
    
            if (email.isEmpty() || password.isEmpty()) {
                System.out.println("All fields are required!");
                return false;
            }
    
            String query = "SELECT * FROM users WHERE email=? AND password=?";
    
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(query)) {
    
                pst.setString(1, email);
                pst.setString(2, password);
    
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    System.out.println("Login Successful! Welcome, " + rs.getString("name"));
                    loggedInUserId = rs.getInt("id"); // Save user ID
                    return true;
                } else {
                    System.out.println("Invalid Email or Password");
                    loggedInUserId = -1;
                    return false;
                }
    
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Error: " + ex.getMessage());
                loggedInUserId = -1;
                return false;
            }
        }
    }
    // ...existing code...
