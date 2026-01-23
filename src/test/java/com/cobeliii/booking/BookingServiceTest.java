package com.cobeliii.booking;

import com.cobeliii.car.Car;
import com.cobeliii.car.CarService;
import com.cobeliii.car.EngineType;
import com.cobeliii.user.User;
import com.cobeliii.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingDao bookingDao;
    @Mock
    private CarService carService;
    @Mock
    private UserService userService;

    @InjectMocks
    private BookingService underTest;

    @Test
    void itShouldPrintBookings(){
        User user = new User( "Jorge");
        Car car = new Car( "Mercedes", "C300", EngineType.ELECTRIC);
        List<Booking> bookings = List.of(
                new Booking(user,car, LocalDateTime.now())
        );

        when(bookingDao.getBookings()).thenReturn(bookings);
        underTest.printBookings();
        verify(bookingDao).getBookings();
    }

    @Test
    void itShouldAddBooking(){

        User user = new User( "Jorge");
        Car car = new Car( "Mercedes", "C300", EngineType.ELECTRIC);
        UUID carId = car.getId();
        UUID userId = user.getId();

        when(carService.findCarById(carId)).thenReturn(car);
        when(userService.findUserById(userId)).thenReturn(user);
        when(bookingDao.saveBooking(any(Booking.class))).thenReturn(true);

        boolean actual = underTest.addBooking(carId, userId);

        verify(carService).findCarById(carId);
        verify(userService).findUserById(userId);

        ArgumentCaptor<Booking> bookingCaptor = ArgumentCaptor.forClass(Booking.class);
        verify(bookingDao).saveBooking(bookingCaptor.capture());

        Booking capturedBooking = bookingCaptor.getValue();

        assertThat(capturedBooking.getCar()).isEqualTo(car);
        assertThat(capturedBooking.getUser()).isEqualTo(user);
        assertThat(capturedBooking.getBookingTime()).isNotNull();

        verify(carService).setRenterName(user.getName(), carId);
        verifyNoMoreInteractions(carService, userService, bookingDao);

        assertThat(actual).isTrue();
    }

    @Test
    void itShouldViewAllUserBookedCars(){
        User user = new User( "Jorge");
        Car car = new Car( "Mercedes", "C300", EngineType.ELECTRIC);
        LocalDateTime now = LocalDateTime.now();
        Booking booking = new Booking(user, car, now);
        when(bookingDao.getBookings()).thenReturn(List.of(booking));
        underTest.viewAllUserBookedCars(user);
        verify(bookingDao).getBookings();
        verifyNoInteractions(carService);
        verifyNoInteractions(userService);
    }
}