package autoservise.service;

import autoservise.model.Car;

public interface CarService {
    Car create(Car car);

    Car update(Car car);
}
