package mate.academy.dto.mapper;

import mate.academy.dto.request.FavorRequestDto;
import mate.academy.dto.response.FavorResponseDto;
import mate.academy.model.Favor;
import mate.academy.model.MasterSalaryStatus;
import org.springframework.stereotype.Component;

@Component
public class FavorMapper {
    public FavorResponseDto toResponseDto(Favor favor) {
        FavorResponseDto dto = new FavorResponseDto();
        dto.setCost(favor.getCost());
        dto.setOrder(favor.getOrder());
        dto.setMaster(favor.getMaster());
        dto.setMasterStatus(String.valueOf(favor.getMasterStatus()));
        dto.setId(favor.getId());
        return dto;
    }

    public Favor toModel(FavorRequestDto dto) {
        Favor favor = new Favor();
        favor.setCost(dto.getCost());
        favor.setOrder(dto.getOrder());
        favor.setMaster(dto.getMaster());
        favor.setMasterStatus(MasterSalaryStatus.valueOf(dto.getMasterStatus()));
        return favor;
    }
}
