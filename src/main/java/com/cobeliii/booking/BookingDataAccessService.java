package com.cobeliii.booking;

import java.util.ArrayList;
import java.util.List;

public class BookingDataAccessService implements BookingDao{
    private static List<Booking> bookings = new ArrayList<>();

    @Override
    public List<Booking> getBookings() {
        return bookings;
    }
streams

    @Override
    public boolean saveBooking(Booking booking) {
        bookings.add(booking);
        return true;
    }
 main
}
