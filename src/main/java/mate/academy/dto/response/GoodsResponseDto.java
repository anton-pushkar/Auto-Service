package mate.academy.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodsResponseDto {
    private Long id;
    private String name;
    private double cost;
}