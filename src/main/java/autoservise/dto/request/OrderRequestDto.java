package autoservise.dto.request;

import autoservise.model.OrderStatus;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long carId;
    private String description;
    private List<Long> favorIds;
    private List<Long> goodsIds;
    private OrderStatus status;
}
