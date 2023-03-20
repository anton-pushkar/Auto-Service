package mate.academy.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.dto.request.MasterRequestDto;
import mate.academy.dto.response.MasterResponseDto;
import mate.academy.model.Master;
import mate.academy.model.Order;
import mate.academy.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class MasterMapper {
    private final OrderRepository repository;

    public MasterMapper(OrderRepository repository) {
        this.repository = repository;
    }

    public Master toModel(MasterRequestDto dto) {
        Master master = new Master();
        master.setName(dto.getName());
        List<Order> orders = dto.getOrdersId().stream()
                .map(repository::getById)
                .collect(Collectors.toList());
        master.setOrdersList(orders);
        return master;
    }

    public MasterResponseDto toResponseDto(Master master) {
        MasterResponseDto dto = new MasterResponseDto();
        dto.setName(master.getName());
        dto.setId(master.getId());
        dto.setOrdersId(master.getOrdersList().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
