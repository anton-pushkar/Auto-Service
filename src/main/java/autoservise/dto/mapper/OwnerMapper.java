package autoservise.dto.mapper;

import autoservise.dto.request.OwnerRequestDto;
import autoservise.dto.response.OwnerResponseDto;
import autoservise.model.Car;
import autoservise.model.Order;
import autoservise.model.Owner;
import autoservise.repository.CarRepository;
import autoservise.repository.OrderRepository;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    private final CarRepository carRepository;
    private final OrderRepository orderRepository;

    public OwnerMapper(CarRepository carRepository, OrderRepository orderRepository) {
        this.carRepository = carRepository;
        this.orderRepository = orderRepository;
    }

    public Owner toModel(OwnerRequestDto dto) {
        Owner owner = new Owner();
        owner.setCarList(dto.getCarIds().stream()
                .map(carRepository::getById)
                .collect(Collectors.toList()));
        owner.setOrders(dto.getOrderIds().stream()
                .map(orderRepository::getById)
                .collect(Collectors.toList()));
        return owner;
    }

    public OwnerResponseDto toResponseDto(Owner owner) {
        OwnerResponseDto dto = new OwnerResponseDto();
        dto.setCarIds(owner.getCarList().stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        dto.setOrderIds(owner.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        dto.setId(owner.getId());
        return dto;
    }
}
