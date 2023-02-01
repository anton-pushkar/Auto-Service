package mate.academy.dto.mapper;

import mate.academy.dto.request.OrderRequestDto;
import mate.academy.dto.response.OrderResponseDto;
import mate.academy.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setCar(dto.getCar());
        order.setCost(dto.getCost());
        order.setStatus(dto.getStatus());
        order.setFavorList(dto.getFavorList());
        order.setFinishedTime(dto.getFinishedTime());
        order.setGoodsList(dto.getGoodsList());
        order.setStartTime(dto.getStartTime());
        order.setDescription(dto.getDescription());
        return order;
    }

    public OrderResponseDto toResponseDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setCar(order.getCar());
        dto.setStartTime(order.getStartTime());
        dto.setCost(order.getCost());
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setFavorList(order.getFavorList());
        dto.setFinishedTime(order.getFinishedTime());
        dto.setDescription(order.getDescription());
        dto.setGoodsList(order.getGoodsList());
        return dto;
    }
}
