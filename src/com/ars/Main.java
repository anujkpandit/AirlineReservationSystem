package com.ars;

import java.util.Scanner;

public class Main {

    private static boolean loggedIn = false;
    private static String loggedInUser = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Airline Reservation System =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. View Flights");
            System.out.println("4. Book Flight");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine().trim();

                        // ...existing code...
            switch (choice) {
                case "1":
                    RegisterUser.register(sc); // Console registration
                    break;
                case "2":
    loggedIn = LoginUser.login(sc); // Save login status
    break;
                case "3":
                    System.out.println("\nAvailable Flights:");
                    ViewFlights.main(null); // Display flights
                    break;
                case "4":
    if (!loggedIn) {
        System.out.println("⚠️ Please login first to book a flight!");
    } else {
        BookFlightConsole.book(sc, LoginUser.getLoggedInUserId());
    }
    break;
        case "5":
            System.out.println("Exiting. Thank you!");
            sc.close();
            System.exit(0);
        default:
            System.out.println("Invalid choice! Try again.");
            }
            
        }
    }
}
