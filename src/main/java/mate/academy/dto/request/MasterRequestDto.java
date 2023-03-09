package mate.academy.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mate.academy.model.Order;

@Getter
@Setter
public class MasterRequestDto {
    private String name;
    private List<Order> ordersList;
}
