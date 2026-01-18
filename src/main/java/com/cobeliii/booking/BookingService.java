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
    private final UserService userService;
    private final CarService carService;
    private final BookingDao bookingDao;


    public BookingService(UserService userService,
                          CarService carService,
                          BookingDao bookingDao) {
        this.bookingDao = bookingDao;
        this.userService = userService;
        this.carService = carService;

    }

    public void printBookings() {
        bookingDao.getBookings().forEach(System.out::println);
    }

    public void addBooking(UUID carId, UUID userId) {
        Car car = carService.findCarById(carId);
        if (car == null) {
            System.out.println("Car not found");
            return;
        }

        User user = userService.findUserById(userId);
        if (user == null) {
            System.out.println("User not found");
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        Booking newBooking = new Booking(user, car, now);
        var booking = bookingDao.saveBooking(newBooking);
        carService.setRenterName(user.getName(), carId);
        System.out.println("Booking added: " + booking);
    }

    public void viewAllUserBookedCars(User user) {

        if (user == null) {
            System.out.println("User not found");
            return;
        }

        bookingDao.getBookings().forEach(booking -> {
            if (booking.getUser().equals(user)) {
                System.out.println(booking.getCar());
            }
        });
    }
}
