package mate.academy.dto.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.dto.request.OrderRequestDto;
import mate.academy.dto.request.OrderRequestDtoForCreate;
import mate.academy.dto.response.OrderResponseDto;
import mate.academy.model.Car;
import mate.academy.model.Favor;
import mate.academy.model.Goods;
import mate.academy.model.Order;
import mate.academy.model.OrderStatus;
import mate.academy.repository.CarRepository;
import mate.academy.repository.FavorRepository;
import mate.academy.repository.GoodsRepository;
import mate.academy.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final OrderService service;
    private final GoodsRepository goodsRepository;
    private final FavorRepository favorRepository;
    private final CarRepository carRepository;

    public OrderMapper(OrderService service, GoodsRepository goodsRepository,
                       FavorRepository favorRepository, CarRepository carRepository) {
        this.service = service;
        this.goodsRepository = goodsRepository;
        this.favorRepository = favorRepository;
        this.carRepository = carRepository;
    }

    public Order toModel(OrderRequestDto dto) {
        Order order = new Order();
        Car car = carRepository.getById(dto.getCarId());
        order.setCar(car);
        List<Goods> goods = dto.getFavorIds().stream()
                .map(goodsRepository::getById)
                .collect(Collectors.toList());
        List<Favor> favors = dto.getFavorIds().stream()
                .map(favorRepository::getById)
                .collect(Collectors.toList());
        order.setFavorList(favors);
        order.setGoodsList(goods);
        order.setCost(service.getOrderCostForCreate(car,
                dto.getStatus(), favors,goods));
        order.setStatus(dto.getStatus());
        order.setDescription(dto.getDescription());
        return order;
    }

    public OrderResponseDto toResponseDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setCarId(order.getCar().getId());
        dto.setStartTime(order.getStartTime());
        dto.setCost(order.getCost());
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setFavorIds(order.getFavorList().stream()
                .map(Favor::getId)
                .collect(Collectors.toList()));
        dto.setFinishedTime(order.getFinishedTime());
        dto.setDescription(order.getDescription());
        dto.setGoodsIds(order.getGoodsList().stream()
                .map(Goods::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    public Order toModelForCreate(OrderRequestDtoForCreate dto) {
        Order order = new Order();
        Car car = carRepository.getById(dto.getCarId());
        order.setCar(car);
        order.setDescription(dto.getDescription());
        List<Favor> favors = dto.getFavorIds().stream()
                .map(favorRepository::getById)
                .collect(Collectors.toList());
        List<Goods> goods = dto.getFavorIds().stream()
                .map(goodsRepository::getById)
                .collect(Collectors.toList());
        order.setStartTime(LocalDateTime.now());
        order.setStatus(OrderStatus.ACCEPTED);
        order.setCost(service.getOrderCostForCreate(car,
                OrderStatus.ACCEPTED, favors, goods));
        return order;
    }
}
