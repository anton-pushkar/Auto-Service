package autoservise.dto.response;

import autoservise.model.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String description;
    private LocalDateTime startTime;
    private List<Long> favorIds;
    private List<Long> goodsIds;
    private OrderStatus status;
    private BigDecimal cost;
    private LocalDateTime finishedTime;
}
