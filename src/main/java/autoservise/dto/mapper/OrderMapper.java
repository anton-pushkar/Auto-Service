package autoservise.dto.mapper;

import autoservise.dto.request.OrderRequestDto;
import autoservise.dto.request.create.OrderRequestDtoForCreate;
import autoservise.dto.response.OrderResponseDto;
import autoservise.model.Car;
import autoservise.model.Favor;
import autoservise.model.Goods;
import autoservise.model.Master;
import autoservise.model.Order;
import autoservise.model.OrderStatus;
import autoservise.model.Owner;
import autoservise.repository.CarRepository;
import autoservise.repository.FavorRepository;
import autoservise.repository.GoodsRepository;
import autoservise.repository.MasterRepository;
import autoservise.repository.OwnerRepository;
import autoservise.service.OrderService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final OrderService service;
    private final GoodsRepository goodsRepository;
    private final FavorRepository favorRepository;
    private final CarRepository carRepository;
    private final MasterRepository masterRepository;
    private final OwnerRepository ownerRepository;

    public OrderMapper(OrderService service, GoodsRepository goodsRepository,
                       FavorRepository favorRepository, CarRepository carRepository,
                       MasterRepository masterRepository, OwnerRepository ownerRepository) {
        this.service = service;
        this.goodsRepository = goodsRepository;
        this.favorRepository = favorRepository;
        this.carRepository = carRepository;
        this.masterRepository = masterRepository;
        this.ownerRepository = ownerRepository;
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
        order.setCost(service.getOrderCostForMapper(car,
                dto.getStatus(), favors,goods));
        order.setStatus(dto.getStatus());
        order.setDescription(dto.getDescription());
        return order;
    }

    public OrderResponseDto toResponseDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setCarId(order.getCar().getId());
        dto.setStartTime(order.getStartTime());
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        if (order.getFavorList() == null) {
            dto.setFavorIds(null);
        } else {
            dto.setFavorIds(order.getFavorList().stream()
                    .map(Favor::getId)
                    .collect(Collectors.toList()));
        }
        dto.setFinishedTime(order.getFinishedTime());
        dto.setDescription(order.getDescription());
        if (order.getGoodsList() == null) {
            dto.setGoodsIds(null);
        } else {
            dto.setGoodsIds(order.getGoodsList().stream()
                    .map(Goods::getId)
                    .collect(Collectors.toList()));
        }
        dto.setCost(service.getOrderCostForMapper(order.getCar(),
                dto.getStatus(), order.getFavorList(),order.getGoodsList()));
        return dto;
    }

    public Order toModelForCreate(OrderRequestDtoForCreate dto) {
        Order order = new Order();
        Car car = carRepository.getById(dto.getCarId());
        order.setCar(car);
        order.setDescription(dto.getDescription());
        order.setStartTime(LocalDateTime.now());
        order.setStatus(OrderStatus.ACCEPTED);
        order.setCost(service.getOrderCostForCreate(car,OrderStatus.ACCEPTED));
        Master masterByName = masterRepository.getMasterByName(dto.getMasterName());
        if (masterByName == null) {
            throw new RuntimeException("You enter wrong master name");
        }
        if (masterByName.getOrdersList() == null) {
            masterByName.setOrdersList(new ArrayList<>());
            List<Order> ordersList = masterByName.getOrdersList();
            ordersList.add(order);
            masterByName.setOrdersList(ordersList);
        } else {
            List<Order> ordersList = masterByName.getOrdersList();
            ordersList.add(order);
            masterByName.setOrdersList(ordersList);
        }

        Owner owner = ownerRepository.getById(dto.getOwnerId());
        if (owner == null) {
            throw new RuntimeException("You enter wrong owner id");
        }
        if (owner.getOrders() == null) {
            owner.setOrders(new ArrayList<>());
            List<Order> orders = owner.getOrders();
            orders.add(order);
            owner.setOrders(orders);
        } else {
            List<Order> orders = owner.getOrders();
            orders.add(order);
            owner.setOrders(orders);
        }
        return order;
    }
}
