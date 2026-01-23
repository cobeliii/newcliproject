package com.cobeliii.car;

import java.util.ArrayList;
import java.util.List;


public class CarDataAccessService implements CarDao{
    private static final List<Car> cars = new ArrayList<>();

    static {
        cars.add(new Car( "Tesla", "Unknown", EngineType.ELECTRIC));
        cars.add(new Car( "BMW", "Unknown", EngineType.PETROL));
        cars.add(new Car( "Volvo", "Unknown", EngineType.PETROL));
        cars.add(new Car( "Toyota", "Highlander", EngineType.PETROL));
        cars.add(new Car( "Mercedes", "G63", EngineType.PETROL));
        cars.add(new Car( "Toyota", "Camry", EngineType.ELECTRIC));
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }
}
