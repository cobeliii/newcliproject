package com.cobeliii.booking;

import com.cobeliii.car.Car;
import com.cobeliii.car.CarService;
import com.cobeliii.user.User;
import com.cobeliii.user.UserService;

import java.time.LocalDateTime;
 streams
import java.util.UUID;

public class BookingService {
    private final UserService userService;
    private final CarService carService;
    private final BookingDao bookingDao;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BookingService {
    private final List<Booking> bookings;
    private final UserService userService;
    private final CarService carService;
    private final BookingDataAccessService data = new BookingDataAccessService();



    public BookingService(UserService userService,
                          CarService carService,
                          BookingDao bookingDao) {
        this.bookingDao = bookingDao;
        this.userService = userService;
        this.carService = carService;

    }

    public void printBookings() {

        bookingDao.getBookings().forEach(System.out::println);

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

    public boolean addBooking(UUID carId , UUID userId) {
        Car car = carService.findCarById(carId);
        if (car == null) {
            System.out.println("Car not found");
            return false;
        }

        User user = userService.findUserById(userId);
        if (user == null) {
            System.out.println("User not found");
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        Booking newBooking = new Booking(userById, carById, now);

        bookings.add(newBooking);

        carService.setRenterName(userById.getName(), carId);

        System.out.println("Booking added");
    }

    public void viewAllUserBookedCars(User user) {

        if (user == null) {
            System.out.println("User not found");
            return;
        }

        bookingDao.getBookings().forEach(booking -> {
            if (booking.getUser().equals(user)) {
                System.out.println(booking.getCar());

        for (Booking booking : bookings) {
            try {
                if (booking.getUser().equals(userByName)) {
                    System.out.println(booking.getCar());
                }
            } catch (Exception e) {
                System.out.print("");
            }
        });
    }
}
