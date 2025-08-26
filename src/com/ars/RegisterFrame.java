package com.ars;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterFrame extends JFrame {
    JTextField tfUser, tfEmail;
    JPasswordField pfPass;

    public RegisterFrame() {
        setTitle("Register");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tfUser = new JTextField(15);
        tfEmail = new JTextField(15);
        pfPass = new JPasswordField(15);
        JButton btnRegister = new JButton("Register");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(tfUser);
        panel.add(new JLabel("Email:"));
        panel.add(tfEmail);
        panel.add(new JLabel("Password:"));
        panel.add(pfPass);
        panel.add(btnRegister);
        add(panel);

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        setVisible(true);
    }

    private void registerUser() {
        String username = tfUser.getText();
        String email = tfEmail.getText();
        String password = new String(pfPass.getPassword());

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration Successful!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
