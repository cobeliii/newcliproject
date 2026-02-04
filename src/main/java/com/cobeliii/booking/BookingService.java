package com.cobeliii.booking;

import com.cobeliii.car.Car;
import com.cobeliii.car.CarService;
import com.cobeliii.exceptions.CarAlreadyTakenObject;
import com.cobeliii.exceptions.ObjectNotFoundException;
import com.cobeliii.user.User;
import com.cobeliii.user.UserService;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
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

    public List<Booking> getBookings() {
        return bookingDao.getBookings();
    }

    // TODO: delete booking and test

    public void deleteBooking(UUID bookingId) {
        Booking retrievedBooking = bookingDao.findBookingById(bookingId); //So I can set renter to null
        bookingDao.deleteBooking(retrievedBooking.getBookingId());
        carService.setRenterName(null, retrievedBooking.getCar().getId());
    }

    public boolean addBooking(UUID carId, UUID userId) {
        Car car = carService.findCarById(carId);

        if (car == null) throw new ObjectNotFoundException("Car not found");

        // TODO: what if the car is taken

        if (car.getRenterName() != null) {
            throw new CarAlreadyTakenObject("Car is already taken");
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ObjectNotFoundException("User not found");
        }

        LocalDateTime now = LocalDateTime.now(clock);
        UUID bookingId = UUID.randomUUID();
        Booking newBooking = new Booking(bookingId,user, car, now);
        // todo: what if the booking fails (done) create a test for it
        var isBooked = bookingDao.saveBooking(newBooking);
        if (!isBooked) {
            return false;
        }
        carService.setRenterName(user.getName(), carId);
        System.out.println("Booking added: " + isBooked);

        return true;
    }

    //todo: change this to return bookedCars
    public List<Booking> getAllUserBookedCars(User user) {
        return bookingDao.getBookings()
                            .stream()
                            .filter(booking -> booking.getUser().equals(user))
                            .toList();

    }
}
