package com.cobeliii.car;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;


import static org.assertj.core.api.Assertions.assertThat;
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
                new Car( "Tesla", "Model 3", EngineType.ELECTRIC)
        );
        when(carDao.getCars()).thenReturn(cars);
        underTest.viewAvailableCars();
        verify(carDao).getCars();
    }

    @Test
    void itShouldViewAvailableElectricCars(){
        List<Car> cars = List.of(
                new Car( "Tesla", "Model 3", EngineType.ELECTRIC)
        );
        when(carDao.getCars()).thenReturn(cars);
        underTest.viewAvailableElectricCars();
        verify(carDao).getCars();
    }


    @Test
    void itShouldFindCarById(){
        Car car = new Car("Tesla", "Model 3", EngineType.ELECTRIC);
        when(carDao.getCars()).thenReturn(List.of(car));
        underTest.findCarById(car.getId());
        verify(carDao).getCars();
    }

    @Test
    void itShouldNotFindCarById(){
        Car car = new Car("Tesla", "Model 3", EngineType.ELECTRIC);
        when(carDao.getCars()).thenReturn(List.of(car));
        var actual = underTest.findCarById(UUID.randomUUID());
        assertThat(actual).isNull();
    }

    @Test
    void itShouldSetRenterName(){
        Car car = new Car("Tesla", "Model 3", EngineType.ELECTRIC);
        when(carDao.getCars()).thenReturn(List.of(car));
        underTest.setRenterName("Jorge", car.getId());

        var actual = underTest.findCarById(car.getId()).getRenterName();
        assertThat(actual).isEqualTo("Jorge");
    }

    @Test
    void itShouldPrintAllCarsWithOwner(){
        String owner = "Jorge";
        Car car = new Car("Tesla", "Model 3", EngineType.ELECTRIC, owner);
        when(carDao.getCars()).thenReturn(List.of(car));
        underTest.printAllCarsWithOwner(owner);
        verify(carDao).getCars();
    }
}