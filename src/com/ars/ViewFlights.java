package com.ars;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewFlights {
    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, flight_no, source, destination, departure_time, arrival_time, price FROM flights")) {

            System.out.println("--------------------------------------------------------------");
            System.out.printf("%-5s %-10s %-12s %-12s %-20s %-20s %-8s\n",
                    "ID", "FlightNo", "Source", "Destination", "Departure", "Arrival", "Price");
            System.out.println("--------------------------------------------------------------");

            boolean hasFlights = false;
            while (rs.next()) {
                hasFlights = true;
                System.out.printf("%-5d %-10s %-12s %-12s %-20s %-20s %-8d\n",
                        rs.getInt("id"),
                        rs.getString("flight_no"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getTimestamp("departure_time"),
                        rs.getTimestamp("arrival_time"),
                        rs.getInt("price"));
            }

            if (!hasFlights) {
                System.out.println("⚠️ No flights available in the system!");
            }

            System.out.println("--------------------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
