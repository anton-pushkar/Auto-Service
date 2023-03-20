package mate.academy.dto.mapper;

import mate.academy.dto.request.FavorRequestDto;
import mate.academy.dto.response.FavorResponseDto;
import mate.academy.model.Favor;
import mate.academy.model.Master;
import mate.academy.model.MasterSalaryStatus;
import mate.academy.model.Order;
import mate.academy.repository.MasterRepository;
import mate.academy.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class FavorMapper {
    private final MasterRepository masterRepository;
    private final OrderRepository orderRepository;

    public FavorMapper(MasterRepository masterRepository, OrderRepository orderRepository) {
        this.masterRepository = masterRepository;
        this.orderRepository = orderRepository;
    }

    public FavorResponseDto toResponseDto(Favor favor) {
        FavorResponseDto dto = new FavorResponseDto();
        dto.setCost(favor.getCost());
        dto.setOrderId(favor.getOrder().getId());
        dto.setMasterId(favor.getMaster().getId());
        dto.setMasterStatus(String.valueOf(favor.getMasterStatus()));
        dto.setId(favor.getId());
        return dto;
    }

    public Favor toModel(FavorRequestDto dto) {
        Favor favor = new Favor();
        favor.setCost(dto.getCost());
        Master masterByName = masterRepository.getMasterByName(dto.getMasterName());
        if (masterByName != null) {
            favor.setMaster(masterByName);
        } else {
            throw new RuntimeException("You enter wrong master name");
        }

        Order byId = orderRepository.getById(dto.getOrderId());
        if (byId != null) {
            favor.setOrder(byId);
        } else {
            throw new RuntimeException("You enter wrong master name");
        }
        favor.setMasterStatus(MasterSalaryStatus.valueOf(dto.getMasterStatus()));
        return favor;
    }
}
