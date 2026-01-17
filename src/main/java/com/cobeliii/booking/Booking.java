package com.cobeliii.booking;

import com.cobeliii.car.Car;
import com.cobeliii.user.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Booking {
    private UUID bookingId;
    private User user;
    private Car car;
    private LocalDateTime bookingTime;


    public Booking(User user, Car car, LocalDateTime bookingTime) {
        this.bookingId = UUID.randomUUID();
        this.user = user;
        this.car = car;
        this.bookingTime = bookingTime;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId) && Objects.equals(user, booking.user) && Objects.equals(car, booking.car) && Objects.equals(bookingTime, booking.bookingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, user, car, bookingTime);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", user=" + user +
                ", car=" + car +
                ", bookingTime=" + bookingTime +
                '}';
    }
}
