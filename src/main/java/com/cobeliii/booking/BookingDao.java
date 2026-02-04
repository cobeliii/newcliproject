package com.cobeliii.booking;

import java.util.List;
import java.util.UUID;

public interface BookingDao {
    List<Booking> getBookings();
    boolean saveBooking(Booking booking);
    void deleteBooking(UUID bookingId);
    Booking findBookingById(UUID bookingId);
}
