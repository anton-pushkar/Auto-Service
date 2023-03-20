package mate.academy.dto.mapper;

import mate.academy.dto.request.CarRequestDto;
import mate.academy.dto.response.CarResponseDto;
import mate.academy.model.Car;
import mate.academy.model.Owner;
import mate.academy.repository.OwnerRepository;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    private final OwnerRepository repository;

    public CarMapper(OwnerRepository repository) {
        this.repository = repository;
    }

    public Car toModel(CarRequestDto dto) {
        Car car = new Car();
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setBrand(dto.getBrand());
        Owner byId = repository.getById(dto.getOwnerId());
        if (byId != null) {
            car.setOwner(byId);
        } else {
            throw new RuntimeException("You enter wrong owner id");
        }
        car.setNumber(dto.getNumber());
        return car;
    }

    public CarResponseDto toResponseDto(Car car) {
        CarResponseDto dto = new CarResponseDto();
        dto.setBrand(car.getBrand());
        dto.setYear(car.getYear());
        dto.setOwnerId(car.getOwner().getId());
        dto.setNumber(car.getNumber());
        dto.setId(car.getId());
        dto.setModel(car.getModel());
        return dto;
    }
}
