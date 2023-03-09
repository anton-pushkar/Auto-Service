package mate.academy.dto.response;

import lombok.Getter;
import lombok.Setter;
import mate.academy.model.Master;
import mate.academy.model.Order;

@Getter
@Setter
public class FavorResponseDto {
    private Long id;
    private Order order;
    private Master master;
    private Double cost;
    private String masterStatus;
}
