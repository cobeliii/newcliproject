package com.cobeliii.car;

import java.util.UUID;

public class CarArrayDataAccessService {
    private static final Car[] cars = new Car[4];

    static {

        cars[0] = new Car(UUID.randomUUID(), "Tesla", "Unknown", EngineType.Electric);
        cars[1] = new Car(UUID.randomUUID(), "Toyota", "Highlander", EngineType.Petrol);
        cars[2] = new Car(UUID.randomUUID(), "Mercedes", "G63", EngineType.Petrol);
        cars[3] = new Car(UUID.randomUUID(), "Toyota", "Camry", EngineType.Electric);
    }

    public Car[] getCars() {
        return cars;
    }
}
