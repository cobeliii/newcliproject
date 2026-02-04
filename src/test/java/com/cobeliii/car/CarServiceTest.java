package com.cobeliii.car;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarDao carDao;

    @InjectMocks
    private CarService underTest;



    @Test
    void itShouldViewAvailableCars(){
        List<Car> cars = List.of(
                new Car( UUID.randomUUID(),"Tesla", "Model 3", EngineType.ELECTRIC)
        );
        when(carDao.getCars()).thenReturn(cars);
        var expected = underTest.getAvailableCars();
        assertThat(expected).isEqualTo(cars);
    }

    @Test
    void itShouldViewAvailableElectricCars(){
        List<Car> cars = List.of(
                new Car( UUID.randomUUID(),"Tesla", "Model 3", EngineType.ELECTRIC)
        );
        when(carDao.getCars()).thenReturn(cars);
        var expected = underTest.getAvailableElectricCars();
        assertThat(expected).isEqualTo(cars);
    }


    @Test
    void itShouldFindCarById(){
        Car car = new Car(UUID.randomUUID(),"Tesla", "Model 3", EngineType.ELECTRIC);
        when(carDao.getCarById(car.getId())).thenReturn(car);
        var expected = underTest.findCarById(car.getId());
        assertThat(expected).isEqualTo(car);
    }


    @Test
    void itShouldSetRenterName(){
        Car car = new Car(UUID.randomUUID(),"Tesla", "Model 3", EngineType.ELECTRIC);
        when(carDao.getCarById(car.getId())).thenReturn(car);
        assertAll(()-> underTest.setRenterName("Jorge", car.getId()));
    }

}