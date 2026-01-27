# Car Booking System (CLI)

A small Java CLI app for booking cars and viewing bookings. It uses in-memory
data stores for cars, users, and bookings and is built with Maven.

## Features
- Book a car for a user
- View all bookings
- View bookings for a specific user
- List available cars and available electric cars
- List all users

## Requirements
- Java 22
- Maven 3.9+

## Run
1. Build the project:
   ```bash
   mvn -q -DskipTests package
   ```
2. Start the CLI:
   ```bash
   java -cp target/classes Main
   ```

## Usage
When the app starts, choose from the menu options:
- Book car
- View all user booked cars
- View all bookings
- View available cars
- View available electric cars
- View all users
- Exit

## Tests
Run unit tests with:
```bash
mvn test
```

## Notes
- Data is stored in memory only; restarting the app resets cars, users, and bookings.
- Initial seed data is defined in `src/main/java/com/cobeliii/car/CarDataAccessService.java`
  and `src/main/java/com/cobeliii/user/UserDataAccessService.java`.
