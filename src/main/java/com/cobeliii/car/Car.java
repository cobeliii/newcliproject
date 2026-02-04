package com.cobeliii.car;

import java.util.Objects;
import java.util.UUID;

public class Car {
    private UUID id;
    private String brand;
    private String model;
    private EngineType engineType;
    private String renterName;

    public Car(UUID id, String brand, String model, EngineType engineType) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.engineType = engineType;
        this.renterName = null;
    }



    public Car(UUID id,String brand, String model, EngineType engineType, String renterName) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.engineType = engineType;
        this.renterName = renterName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && engineType == car.engineType && Objects.equals(renterName, car.renterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, engineType, renterName);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", engineType=" + engineType +
                ", renterName='" + renterName + '\'' +
                '}';
    }
}
