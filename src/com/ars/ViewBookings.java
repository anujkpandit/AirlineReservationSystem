package com.ars;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewBookings extends JFrame {

    private JTable table;

    public ViewBookings() {
        setTitle("View Bookings");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadBookings();
    }

    private void loadBookings() {
        String[] columns = {"Booking ID", "User ID", "Flight ID", "Booking Time"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String query = "SELECT * FROM bookings";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("flight_id"),
                        rs.getTimestamp("booking_time")
                };
                model.addRow(row);
            }

            table.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewBookings().setVisible(true));
    }
}
