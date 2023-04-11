package autoservise.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavorRequestDto {
    private Long orderId;
    private String masterName;
    private BigDecimal cost;
    private String masterStatus;
}
