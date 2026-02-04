package com.cobeliii.car;


import com.cobeliii.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.cobeliii.car.EngineType.ELECTRIC;

public class CarService {
    private final CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public List<Car> getAvailableCars(){
        return carDao.getCars().stream()
                .filter(car -> car.getRenterName() == null)
                .toList();
    }

    // TODO: you know what to do
    public List<Car> getAvailableElectricCars(){
        return carDao.getCars().stream()
                .filter(car -> car.getRenterName() == null && car.getEngineType() == ELECTRIC)
                .toList();
    }

    // TODO: fix this method and exceptions
    public Car findCarById(UUID id) {
        return carDao.getCarById(id);

    }

    // TODO: what is car id does not exit? maybe Optionals
    public void setRenterName(String renterName, UUID id) {
        Car car = carDao.getCarById(id);

        car.setRenterName(renterName);
    }

}
