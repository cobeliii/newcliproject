package com.cobeliii.car;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarDataAccessService implements CarDao{
    private static final List<Car> cars = new ArrayList<>();

    static {
        cars.add(new Car(UUID.randomUUID(), "Tesla", "Unknown", EngineType.ELECTRIC));
        cars.add(new Car(UUID.randomUUID(), "BMW", "Unknown", EngineType.PETROL));
        cars.add(new Car(UUID.randomUUID(), "Volvo", "Unknown", EngineType.PETROL));
        cars.add(new Car(UUID.randomUUID(), "Toyota", "Highlander", EngineType.PETROL));
        cars.add(new Car(UUID.randomUUID(), "Mercedes", "G63", EngineType.PETROL));
        cars.add(new Car(UUID.randomUUID(), "Toyota", "Camry", EngineType.ELECTRIC));
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }
}
