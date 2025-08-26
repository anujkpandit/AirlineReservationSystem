# ✈️ Airline Reservation System (Java + MySQL)

A simple **Airline Reservation System** built in **Java (JDBC)** with **MySQL** database.  
It allows users to register, login, search flights, and make bookings securely.

---

## 🚀 Features

- 👤 **User Management**
  - Register new users
  - Login with credentials
- 🛫 **Flight Management**
  - View available flights
  - Search flights
- 📑 **Booking System**
  - Book a flight
  - Store booking details in database
- 🔒 **Secure Database Connection**
  - Credentials stored in `config/db_config.properties` (ignored by Git)
  - Safe sample config provided for setup

---

## 🛠️ Tech Stack

- Java (Core + JDBC)
- MySQL Database
- GitHub for Version Control

---

## 📂 Project Structure

```
AirlineReservationSystem/
├── .gitignore
├── README.md
├── lib/
│   └── mysql-connector-j-9.4.0.jar
├── config/
│   ├── db_config.properties          # (your local credentials, not pushed to GitHub)
│   └── db_config_sample.properties   # (demo config file for others)
├── src/
│   └── com/
│        └── ars/
│             ├── Main.java
│             ├── DBConnection.java
│             ├── RegisterUser.java
│             ├── LoginUser.java
│             ├── FlightService.java
│             └── BookingService.java
└── build/   # compiled .class files
```

---

## ⚡ Setup & Run

### 1️⃣ Clone the Repository

```sh
git clone https://github.com/anujkpandit/AirlineReservationSystem.git
cd AirlineReservationSystem
```

### 2️⃣ Configure Database

Create a MySQL database (example: `airline`).  
Import your tables (`users`, `flights`, `bookings`).  
Copy `config/db_config_sample.properties` → rename to `db_config.properties`.  
Update it with **your MySQL username & password**:

```properties
db.url=jdbc:mysql://localhost:3306/airline
db.user=root
db.password=your_password_here
```

### 3️⃣ Compile the Project

```sh
javac -cp ".;lib/mysql-connector-j-9.4.0.jar" src/com/ars/*.java
```

### 4️⃣ Run the Project

```sh
java -cp ".;lib/mysql-connector-j-9.4.0.jar;src" com.ars.Main
```

---

## 📖 Example Tables (MySQL)

### `users`

```sql
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(100)
);
```

### `flights`

```sql
CREATE TABLE flights (
  id INT AUTO_INCREMENT PRIMARY KEY,
  flight_number VARCHAR(50),
  source VARCHAR(100),
  destination VARCHAR(100),
  departure_time DATETIME
);
```

### `bookings`

```sql
CREATE TABLE bookings (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  flight_id INT,
  booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (flight_id) REFERENCES flights(id)
);
```

---

## 🤝 Contributing

1. Fork the repo
2. Create a new branch (`feature-xyz`)
3. Commit changes
4. Open a Pull Request

---

## 📜 License

This project is for **educational purposes**. Feel free to use & modify.
