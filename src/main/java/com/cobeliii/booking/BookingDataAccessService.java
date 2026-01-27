package com.cobeliii.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingDataAccessService implements BookingDao{
    private static List<Booking> bookings = new ArrayList<>();

    @Override
    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public boolean saveBooking(Booking booking) {
        bookings.add(booking);
        return true;
    }

    @Override
    public void deleteBooking(Booking booking) {
        bookings.remove(booking);
    }

    @Override
    public Booking findBookingById(UUID bookingId) {
        return bookings.stream()
                .filter(booking -> booking.getBookingId() == bookingId)
                .findFirst().orElse(null);
    }
}
