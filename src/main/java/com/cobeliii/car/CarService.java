package com.cobeliii.car;


import com.cobeliii.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static com.cobeliii.car.EngineType.ELECTRIC;

public class CarService {
    private final CarDao carDao;
    private final List<Car> cars;
    private final CarDataAccessService data = new CarDataAccessService();

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

    // TODO: fix this method and excepitons
    public Car findCarById(UUID id) {
        return carDao.getCars().stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException("Car not found"));

    }

    // TODO: what is car id does not exit? maybe Optionals
    public void setRenterName(String renterName, UUID id) {
        Car car = carDao.getCarById(id);
        if (car == null) {
            throw new ObjectNotFoundException("Car not found");
        }

        car.setRenterName(renterName);
    }


    //Do not need this class
    // TODO: return cas with owner
//    public List<Car> getAllCarsWithOwner(String owner) {
//        return carDao.getCars().stream()
//                .filter(car -> owner.equalsIgnoreCase(car.getRenterName()))
//                .toList();
//    }
}
