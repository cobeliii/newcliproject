package com.cobeliii.booking;

public class BookingListDataAccessService implements BookingDao{
    private static Booking[] bookings = new Booking[10];


    @Override
    public Booking[] getBookings() {
        return bookings;
    }
}
