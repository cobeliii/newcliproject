package com.cobeliii.booking;

import com.cobeliii.car.Car;
import com.cobeliii.car.CarService;
import com.cobeliii.user.User;
import com.cobeliii.user.UserService;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookingService {
    private final UserService userService;
    private final CarService carService;
    private final BookingDao bookingDao;
    private final Clock clock;


    public BookingService(UserService userService,
                          CarService carService,
                          BookingDao bookingDao,
                          Clock clock) {
        this.bookingDao = bookingDao;
        this.userService = userService;
        this.carService = carService;

        this.clock = clock;
    }

    public List<Booking> printBookings() {
        return bookingDao.getBookings();
    }

    // TODO: delete booking and test

    public boolean addBooking(UUID carId, UUID userId) {
        Car car = carService.findCarById(carId);
        // TODO: what if the car is taken
        if (car == null) {
            System.out.println("Car not found");
            return false;
        }

        User user = userService.findUserById(userId);
        if (user == null) {
            System.out.println("User not found");
            return false;
        }

        LocalDateTime now = LocalDateTime.now(clock);
        Booking newBooking = new Booking(user, car, now);
        // todo: what if the booking fails (done) create a test for it
        var isBooked = bookingDao.saveBooking(newBooking);
        if (!isBooked) {
            return false;
        }
        carService.setRenterName(user.getName(), carId);
        System.out.println("Booking added: " + isBooked);

        return true;
    }

    // change this to return bookedCars
    public void viewAllUserBookedCars(User user) {
        Optional.ofNullable(user)
                .ifPresentOrElse((u) -> {
                    bookingDao.getBookings()
                            .stream()
                            .filter(booking -> booking.getUser().equals(u))
                            .forEach(System.out::println);
                }, () -> System.out.println("User not found"));
    }
}
