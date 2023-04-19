package autoservise.dto.request.create;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavorRequestDtoForCreate {
    private Long orderId;
    private String masterName;
    private BigDecimal cost;
    private String masterStatus;
}
