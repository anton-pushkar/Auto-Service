package autoservise.dto.mapper;

import autoservise.dto.request.CarRequestDto;
import autoservise.dto.request.create.CarRequestDtoForCreate;
import autoservise.dto.response.CarResponseDto;
import autoservise.model.Car;
import autoservise.model.Owner;
import autoservise.repository.OwnerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    private final OwnerRepository repository;

    public CarMapper(OwnerRepository repository) {
        this.repository = repository;
    }

    public Car toModelForCreate(CarRequestDtoForCreate dto) {
        Car car = new Car();
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setBrand(dto.getBrand());
        car.setNumber(dto.getNumber());
        Owner owner = repository.getById(dto.getOwnerId());
        if (owner != null) {
            car.setOwner(owner);
            if (owner.getCarList() == null) {
                owner.setCarList(new ArrayList<>());
                List<Car> carList = owner.getCarList();
                carList.add(car);
                owner.setCarList(carList);
            } else {
                List<Car> carList = owner.getCarList();
                carList.add(car);
                owner.setCarList(carList);
            }
        } else {
            throw new RuntimeException("You enter wrong owner id");
        }
        return car;
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
