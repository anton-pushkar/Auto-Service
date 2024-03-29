package autoservise.dto.mapper;

import autoservise.dto.request.FavorRequestDto;
import autoservise.dto.request.create.FavorRequestDtoForCreate;
import autoservise.dto.response.FavorResponseDto;
import autoservise.model.Favor;
import autoservise.model.Master;
import autoservise.model.MasterSalaryStatus;
import autoservise.model.Order;
import autoservise.repository.MasterRepository;
import autoservise.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
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

    public Favor toModelForCreate(FavorRequestDtoForCreate dto) {
        Favor favor = new Favor();
        favor.setCost(dto.getCost());
        favor.setMasterStatus(MasterSalaryStatus.valueOf(dto.getMasterStatus()));
        Master masterByName = masterRepository.getMasterByName(dto.getMasterName());
        if (masterByName != null) {
            favor.setMaster(masterByName);
        } else {
            throw new RuntimeException("You enter wrong master name");
        }

        Order order = orderRepository.getById(dto.getOrderId());
        if (order != null) {
            favor.setOrder(order);
            if (order.getFavorList() == null) {
                order.setFavorList(new ArrayList<>());
                List<Favor> favorList = order.getFavorList();
                favorList.add(favor);
                order.setFavorList(favorList);
            } else {
                List<Favor> favorList = order.getFavorList();
                favorList.add(favor);
                order.setFavorList(favorList);
            }
        } else {
            throw new RuntimeException("You enter wrong master name");
        }
        return favor;
    }
}
