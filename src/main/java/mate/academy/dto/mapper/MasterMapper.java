package mate.academy.dto.mapper;

import mate.academy.dto.request.MasterRequestDto;
import mate.academy.dto.response.MasterResponseDto;
import mate.academy.model.Master;
import org.springframework.stereotype.Component;

@Component
public class MasterMapper {
    public Master toModel(MasterRequestDto dto) {
        Master master = new Master();
        master.setName(dto.getName());
        master.setOrdersList(dto.getOrdersList());
        return master;
    }

    public MasterResponseDto toResponseDto(Master master) {
        MasterResponseDto dto = new MasterResponseDto();
        dto.setName(master.getName());
        dto.setId(master.getId());
        dto.setOrdersList(master.getOrdersList());
        return dto;
    }
}
