package mate.academy.dto.mapper;

import mate.academy.dto.request.OwnerRequestDto;
import mate.academy.dto.response.OwnerResponseDto;
import mate.academy.model.Owner;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    public Owner toModel(OwnerRequestDto dto) {
        Owner owner = new Owner();
        owner.setCarList(dto.getCarList());
        owner.setOrders(dto.getOrders());
        return owner;
    }

    public OwnerResponseDto toResponseDto(Owner owner) {
        OwnerResponseDto dto = new OwnerResponseDto();
        dto.setCarList(owner.getCarList());
        dto.setOrders(owner.getOrders());
        dto.setId(owner.getId());
        return dto;
    }
}
