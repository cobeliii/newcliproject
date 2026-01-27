package com.cobeliii.car;

import java.util.List;
import java.util.UUID;

public interface CarDao {
    List<Car> getCars();
    Car getCarById(UUID id);

}
