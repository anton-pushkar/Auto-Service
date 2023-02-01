package mate.academy.dto.request;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mate.academy.model.Car;
import mate.academy.model.Favor;
import mate.academy.model.Goods;
import mate.academy.model.OrderStatus;

@Getter
@Setter
public class OrderRequestDto {
    private Car car;
    private String description;
    private LocalDateTime startTime;
    private List<Favor> favorList;
    private List<Goods> goodsList;
    private OrderStatus status;
    private double cost;
    private LocalDateTime finishedTime;
}
