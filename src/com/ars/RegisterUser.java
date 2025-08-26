package com.ars;

import java.sql.*;
import java.util.Scanner;

public class RegisterUser {

    // Console-friendly static method
    public static void register(Scanner sc) {
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = sc.nextLine().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("All fields are required!");
            return;
        }

        String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("User Registered Successfully!");
            } else {
                System.out.println("Registration Failed!");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Email already exists, choose another!");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
