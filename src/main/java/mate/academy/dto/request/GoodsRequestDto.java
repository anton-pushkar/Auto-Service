package mate.academy.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsRequestDto {
    private String name;
    private BigDecimal cost;
}
