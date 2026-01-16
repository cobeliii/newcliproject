package com.cobeliii.car;

import java.util.List;
import java.util.UUID;

import static com.cobeliii.car.EngineType.Electric;

public class CarService {
    private Car[] cars;
    private CarArrayDataAccessService data = new CarArrayDataAccessService();

    public CarService() {
        this.cars = data.getCars();
    }

    public void viewAvailableCars(){
        for (Car car : cars) {
            if (car.getRenterName() == null){
                System.out.println(car);
            }
        }
    }

    public void viewAvailableElectricCars(){
        for (Car car : cars) {
            if (car.getRenterName() == null && car.getEngineType() == Electric){
                System.out.println(car);
            }
        }
    }

    public Car findCarById(UUID id) {
        for (Car car : cars) {
            if (car.getId().equals(id)){
                return car;
            }
        }

        return null;
    }

    public void setRenterName(String renterName, UUID id) {
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                car.setRenterName(renterName);
            }
        }
    }

    public void printAllCarsWithOwner(String owner) {
        for (Car car : cars) {
            if (owner.equalsIgnoreCase(car.getRenterName())) {
                System.out.println(car);
            }
        }
    }
}
