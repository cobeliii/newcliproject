package com.cobeliii.car;

import java.util.UUID;

public class Car {
    private UUID id;
    private String brand;
    private String model;
    private EngineType engineType;
    private String renterName;

    public Car(UUID id, String brand, String model, EngineType engineType) {
        this.id = UUID.randomUUID();
        this.brand = brand;
        this.model = model;
        this.engineType = engineType;
        this.renterName = null;
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
