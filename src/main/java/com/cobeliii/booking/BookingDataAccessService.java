package com.cobeliii.booking;

import com.cobeliii.exceptions.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookingDataAccessService implements BookingDao{

    private static List<Booking> bookings = new ArrayList<>();

    @Override
    public List<Booking> getBookings() {

        if (bookings.isEmpty()) {
            throw new ObjectNotFoundException("No bookings found");
        }

        return bookings;
    }

    @Override
    public boolean saveBooking(Booking booking) {
        bookings.add(booking);
        return true;
    }

    @Override
    public void deleteBooking(UUID bookingId) {
        Booking foundBooking = bookings.stream()
                .filter(booking -> booking.getBookingId().equals(bookingId))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Booking not found"));

        bookings.remove(foundBooking);
    }

    @Override
    public Booking findBookingById(UUID bookingId) {
        return bookings.stream()
                .filter(booking -> booking.getBookingId().equals(bookingId))
                .findFirst().orElseThrow(()-> new ObjectNotFoundException("Booking not found"));
    }
}
