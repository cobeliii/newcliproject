package com.cobeliii.car;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarDataAccessService implements CarDao{
    private static final List<Car> cars = new ArrayList<>();

    static {
        cars.add(new Car(UUID.randomUUID(), "Tesla", "Unknown", EngineType.Electric));
        cars.add(new Car(UUID.randomUUID(), "BMW", "Unknown", EngineType.Petrol));
        cars.add(new Car(UUID.randomUUID(), "Volvo", "Unknown", EngineType.Petrol));
        cars.add(new Car(UUID.randomUUID(), "Toyota", "Highlander", EngineType.Petrol));
        cars.add(new Car(UUID.randomUUID(), "Mercedes", "G63", EngineType.Petrol));
        cars.add(new Car(UUID.randomUUID(), "Toyota", "Camry", EngineType.Electric));
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }
}
