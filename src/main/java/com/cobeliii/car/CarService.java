package com.cobeliii.car;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.cobeliii.car.EngineType.ELECTRIC;

public class CarService {
    private final CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public List<Car> viewAvailableCars(){
        return carDao.getCars().stream()
                .filter(car -> car.getRenterName() == null)
                .toList();
    }

    // TODO: you know what to do
    public void viewAvailableElectricCars(){
        carDao.getCars().stream()
                .filter(car -> car.getRenterName() == null && car.getEngineType() == ELECTRIC)
                .forEach(System.out::println);
    }

    // TODO: fix this method and excepitons
    public Car findCarById(UUID id) {
        Stream<Car> car = carDao.getCars().stream()
                .filter(c -> id.equals(c.getId()));

        return car.findFirst().orElse(null);
    }

    // TODO: what is car id does not exit? maybe Optionals
    public void setRenterName(String renterName, UUID id) {
        findCarById(id).setRenterName(renterName);
    }


    // TODO: return cas with owner
    public void printAllCarsWithOwner(String owner) {
        carDao.getCars().stream()
                .filter(car -> owner.equalsIgnoreCase(car.getRenterName()))
                .forEach(System.out::println);
    }
}
