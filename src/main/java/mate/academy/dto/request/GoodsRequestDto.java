package mate.academy.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodsRequestDto {
    private String name;
    private double cost;
}
