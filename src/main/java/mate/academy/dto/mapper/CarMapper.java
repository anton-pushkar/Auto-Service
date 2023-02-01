package mate.academy.dto.mapper;

import mate.academy.dto.request.CarRequestDto;
import mate.academy.dto.response.CarResponseDto;
import mate.academy.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public Car toModel(CarRequestDto dto) {
        Car car = new Car();
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setBrand(dto.getBrand());
        car.setOwner(dto.getOwner());
        car.setNumber(dto.getNumber());
        return car;
    }

    public CarResponseDto toResponseDto(Car car) {
        CarResponseDto dto = new CarResponseDto();
        dto.setBrand(car.getBrand());
        dto.setYear(car.getYear());
        dto.setOwner(car.getOwner());
        dto.setNumber(car.getNumber());
        dto.setId(car.getId());
        dto.setModel(car.getModel());
        return dto;
    }
}
