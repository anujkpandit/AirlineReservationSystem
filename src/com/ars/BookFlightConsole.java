package com.ars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BookFlightConsole {

    public static void book(Scanner sc, int loggedInUserId) {
        if (loggedInUserId <= 0) {
            System.out.println("⚠️ Please login first to book a flight!");
            return;
        }

        System.out.print("Enter Flight Number to book: ");
        String flightNo = sc.nextLine().trim();

        try (Connection con = DBConnection.getConnection()) {
            int flightId = -1;

            // Step 1: Get flight ID from flight number
            String getIdQuery = "SELECT id FROM flights WHERE flight_no=?";
            try (PreparedStatement pst1 = con.prepareStatement(getIdQuery)) {
                pst1.setString(1, flightNo);
                ResultSet rs = pst1.executeQuery();

                if (rs.next()) {
                    flightId = rs.getInt("id");
                } else {
                    System.out.println("❌ Flight not found!");
                    return;
                }
            }

            // Step 2: Insert booking
            String insertQuery = "INSERT INTO bookings (user_id, flight_id, booking_time) VALUES (?, ?, NOW())";
            try (PreparedStatement pst2 = con.prepareStatement(insertQuery)) {
                pst2.setInt(1, loggedInUserId);
                pst2.setInt(2, flightId);

                int rows = pst2.executeUpdate();
                if (rows > 0) {
                    System.out.println("✅ Flight booked successfully!");
                } else {
                    System.out.println("❌ Booking failed!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("⚠️ Error: " + e.getMessage());
        }
    }
}
