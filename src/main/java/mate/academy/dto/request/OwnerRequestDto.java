package mate.academy.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mate.academy.model.Car;
import mate.academy.model.Order;

@Getter
@Setter
public class OwnerRequestDto {
    private List<Car> carList;
    private List<Order> orders;
}
