package com.cobeliii.car;


import java.util.UUID;
import java.util.stream.Stream;

import static com.cobeliii.car.EngineType.ELECTRIC;

public class CarService {
    private final CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public void viewAvailableCars(){
        carDao.getCars().stream()
                .filter(car -> car.getRenterName() == null)
                .forEach(System.out::println);
    }

    public void viewAvailableElectricCars(){
        carDao.getCars().stream()
                .filter(car -> car.getRenterName() == null && car.getEngineType() == ELECTRIC)
                .forEach(System.out::println);
    }

    public Car findCarById(UUID id) {
        Stream<Car> car = carDao.getCars().stream()
                .filter(c -> id.equals(c.getId()));

        return car.findFirst().orElse(null);
    }

    public void setRenterName(String renterName, UUID id) {
        findCarById(id).setRenterName(renterName);
    }


    public void printAllCarsWithOwner(String owner) {
        carDao.getCars().stream()
                .filter(car -> owner.equalsIgnoreCase(car.getRenterName()))
                .forEach(System.out::println);
    }
}
