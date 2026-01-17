package com.cobeliii.booking;

import com.cobeliii.car.Car;
import com.cobeliii.car.CarService;
import com.cobeliii.user.User;
import com.cobeliii.user.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BookingService {
    private final List<Booking> bookings;
    private final UserService userService;
    private final CarService carService;
    private final BookingDataAccessService data = new BookingDataAccessService();


    public BookingService(UserService userService, CarService carService) {
        this.bookings = data.getBookings();
        this.userService = userService;
        this.carService = carService;

    }

    public void printBookings() {
        int numberOfNullBookings = 0;
        for (Booking booking : bookings) {
            if(booking == null) {
                numberOfNullBookings++;
            }
        }

        if(numberOfNullBookings == bookings.size()) {
            System.out.println("No bookings found");
        }else{
            for (Booking booking : bookings) {
                if (booking != null) {
                    System.out.println(booking);
                }
            }
        }
    }

    public void addBooking() {
        Scanner scanner = new Scanner(System.in);
        carService.viewAvailableCars();
        System.out.println("Enter car id: ");
        UUID carId = UUID.fromString(scanner.nextLine());
        Car carById = carService.findCarById(carId);
        if (carById == null) {
            System.out.println("Car not found");
            return;
        }

        userService.printUsers();
        System.out.println("Enter user id:");
        UUID userId = UUID.fromString(scanner.nextLine());
        User userById = userService.findUserById(userId);
        if (userById == null) {
            System.out.println("User not found");
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        Booking newBooking = new Booking(userById, carById, now);

        bookings.add(newBooking);

        carService.setRenterName(userById.getName(), carId);

        System.out.println("Booking added");
    }

    public void viewAllUserBookedCars() {
        Scanner scanner = new Scanner(System.in);
        userService.printUsers();
        System.out.println("Enter user name: ");
        String userName = scanner.nextLine();
        User userByName = userService.findUserByName(userName);
        if (userByName == null) {
            System.out.println("User not found");
            return;
        }


        for (Booking booking : bookings) {
            try {
                if (booking.getUser().equals(userByName)) {
                    System.out.println(booking.getCar());
                }
            } catch (Exception e) {
                System.out.print("");
            }
        }
    }
}
