package autoservise.dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavorResponseDto {
    private Long id;
    private Long orderId;
    private Long masterId;
    private BigDecimal cost;
    private String masterStatus;
}
