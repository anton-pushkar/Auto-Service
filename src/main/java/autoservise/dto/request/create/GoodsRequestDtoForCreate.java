package autoservise.dto.request.create;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsRequestDtoForCreate {
    private String name;
    private BigDecimal cost;
    private Long orderId;
}
