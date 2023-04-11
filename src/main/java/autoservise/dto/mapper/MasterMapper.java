package autoservise.dto.mapper;

import autoservise.dto.request.MasterRequestDto;
import autoservise.dto.response.MasterResponseDto;
import autoservise.model.Master;
import autoservise.model.Order;
import autoservise.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;
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
        List<Order> orders = dto.getOrderIds().stream()
                .map(repository::getById)
                .collect(Collectors.toList());
        master.setOrdersList(orders);
        return master;
    }

    public MasterResponseDto toResponseDto(Master master) {
        MasterResponseDto dto = new MasterResponseDto();
        dto.setName(master.getName());
        dto.setId(master.getId());
        dto.setOrderIds(master.getOrdersList().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
