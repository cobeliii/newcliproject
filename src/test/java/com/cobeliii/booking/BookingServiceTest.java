package com.cobeliii.booking;

import com.cobeliii.car.Car;
import com.cobeliii.car.CarService;
import com.cobeliii.car.EngineType;
import com.cobeliii.exceptions.CarAlreadyTakenObject;
import com.cobeliii.exceptions.ObjectNotFoundException;
import com.cobeliii.user.User;
import com.cobeliii.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    public static final LocalDateTime NOW = LocalDateTime.of(2020, 1, 1, 0, 0);
    @Mock
    private BookingDao bookingDao;
    @Mock
    private CarService carService;
    @Mock
    private UserService userService;
    @Mock
    private Clock clock;

    @BeforeEach
    void setUp() {

    }

    @InjectMocks
    private BookingService underTest;

    @Test
    void itShouldGetBookings() {
        User user = new User("Jorge");
        Car car = new Car("Mercedes", "C300", EngineType.ELECTRIC);
        List<Booking> bookings = List.of(
                new Booking(user, car, LocalDateTime.now())
        );

        when(bookingDao.getBookings()).thenReturn(bookings);
        var expected = underTest.getBookings();
        assertThat(expected).isEqualTo(bookings);
    }



    @Test
    void itShouldAddBooking() {

        User user = new User("Jorge");
        Car car = new Car("Mercedes", "C300", EngineType.ELECTRIC);
        UUID carId = car.getId();
        UUID userId = user.getId();

        when(clock.instant()).thenReturn(NOW.toInstant(ZoneOffset.UTC));
        when(clock.getZone()).thenReturn(ZoneId.of("Z"));
        when(carService.findCarById(carId)).thenReturn(car);
        when(userService.findUserById(userId)).thenReturn(user);
        when(bookingDao.saveBooking(any(Booking.class))).thenReturn(true);

        boolean actual = underTest.addBooking(carId, userId);

        ArgumentCaptor<Booking> bookingCaptor = ArgumentCaptor.forClass(Booking.class);
        verify(bookingDao).saveBooking(bookingCaptor.capture());

        Booking capturedBooking = bookingCaptor.getValue();

        assertThat(capturedBooking.getCar()).isEqualTo(car);
        assertThat(capturedBooking.getUser()).isEqualTo(user);
        assertThat(capturedBooking.getBookingTime()).isEqualTo(NOW);
        System.out.println(capturedBooking.getBookingTime());

        verify(carService).setRenterName(user.getName(), carId);
        verifyNoMoreInteractions(carService, userService, bookingDao);

        assertThat(actual).isTrue();
    }

    @Test
    void itShouldNotAddBookingIfCarIsTaken() {
        User user = new User("Jorge");
        Car car = new Car("Mercedes", "C300", EngineType.ELECTRIC, user.getName()); // taken
        UUID carId = car.getId();
        UUID userId = user.getId();

        when(carService.findCarById(carId)).thenReturn(car);

        assertThatThrownBy(() -> underTest.addBooking(carId, userId))
                .isInstanceOf(CarAlreadyTakenObject.class)
                .hasMessageContaining("Car is already taken");

        verify(bookingDao, never()).saveBooking(any());
        verify(carService, never()).setRenterName(anyString(), any());
        verifyNoInteractions(userService);
    }

    @Test
    void itShouldNotAddBookingIfCarIsNull() {
        User user = new User("Jorge");
        Car car = null;
        UUID carId = UUID.randomUUID();
        UUID userId = user.getId();

        when(carService.findCarById(carId)).thenReturn(car);

        assertThatThrownBy(() -> underTest.addBooking(carId, userId))
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessageContaining("Car not found");

        verify(bookingDao, never()).saveBooking(any());
        verify(carService, never()).setRenterName(anyString(), any());
        verifyNoInteractions(userService);
    }

    @Test
    void itShouldNotAddBookingWhenUserIsNull() {
        User user = null;
        Car car = new Car("Mercedes", "C300", EngineType.ELECTRIC); // taken
        UUID carId = car.getId();
        UUID userId = UUID.randomUUID();

        when(carService.findCarById(carId)).thenReturn(car);
        when(userService.findUserById(userId)).thenReturn(user);

        assertThatThrownBy(() -> underTest.addBooking(carId, userId))
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessageContaining("User not found");

        verify(bookingDao, never()).saveBooking(any());
        verify(carService, never()).setRenterName(anyString(), any());

    }

    @Test
    void itShouldFailToAddBooking(){
        User user = new User("Jorge");
        Car car = new Car("Mercedes", "C300", EngineType.ELECTRIC); // taken
        UUID carId = car.getId();
        UUID userId = user.getId();

        when(clock.instant()).thenReturn(NOW.toInstant(ZoneOffset.UTC));
        when(clock.getZone()).thenReturn(ZoneId.of("Z"));
        when(carService.findCarById(carId)).thenReturn(car);
        when(userService.findUserById(userId)).thenReturn(user);
        when(bookingDao.saveBooking(any(Booking.class))).thenReturn(false);

        boolean actual = underTest.addBooking(carId, userId);

        assertThat(actual).isFalse();
    }

    @Test
    void itShouldGetAllUserBookedCars() {
        User user = new User("Jorge");
        List<Booking> bookings = bookingDao.getBookings();
        when(underTest.getAllUserBookedCars(user)).thenReturn(bookings);
        List<Booking> allUserBookedCars = underTest.getAllUserBookedCars(user);

        assertThat(allUserBookedCars).isEqualTo(bookings);

    }

    @Test
    void itShouldDeleteBooking(){
        User user = new User("Jorge");
        Car car = new Car("Tesla", "Model 3", EngineType.ELECTRIC);
        UUID bookingId = UUID.randomUUID();
        Booking booking = new Booking(bookingId, user, car);

        when(bookingDao.findBookingById(bookingId))
                .thenReturn(booking);

        assertAll(() -> underTest.deleteBooking(bookingId));
    }

    @Test
    void itShouldThrowExceptionWhenTryingToDeleteBooking(){
        User user = new User("Jorge");
        Car car = new Car("Tesla", "Model 3", EngineType.ELECTRIC);
        Booking booking = new Booking(null, user, car);

        assertThatThrownBy(() -> underTest.deleteBooking(booking.getBookingId()))
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessageContaining("Booking not found");
    }


}