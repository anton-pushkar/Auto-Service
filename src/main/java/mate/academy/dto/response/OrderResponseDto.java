package mate.academy.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mate.academy.model.OrderStatus;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String description;
    private LocalDateTime startTime;
    private List<Long> favorsId;
    private List<Long> goodsId;
    private OrderStatus status;
    private BigDecimal cost;
    private LocalDateTime finishedTime;
}
