package com.cobeliii.booking;

import com.cobeliii.car.Car;
import com.cobeliii.car.CarService;
import com.cobeliii.user.User;
import com.cobeliii.user.UserService;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class BookingService {
    private Booking[] bookings;
    private UserService userService;
    private CarService carService;
    private BookingDao bookingDAO = new BookingListDataAccessService();

//    public BookingService(UserService userService, CarService carService) {
//        bookings = bookingDAO.getBookings();
//        this.userService = userService;
//        this.carService = carService;
//    }

    public BookingService(UserService userService, CarService carService) {
        this.bookings = bookingDAO.getBookings();
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

        if(numberOfNullBookings == bookings.length) {
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

        //bookings.add(newBooking);
        for (int i = 0; i < bookings.length; i++) {
            if(bookings[i] == null) {
                bookings[i] = newBooking;
                break;
            }
        }
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


        for (int i = 0; i < bookings.length; i++) {
            try {
                if (bookings[i].getUser().equals(userByName)) {
                    System.out.println(bookings[i]);
                }
            } catch (Exception e) {
                System.out.print("");
            }
        }
    }
}
