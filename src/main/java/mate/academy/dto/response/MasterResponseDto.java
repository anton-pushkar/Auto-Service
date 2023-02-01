package mate.academy.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mate.academy.model.Order;

@Getter
@Setter
public class MasterResponseDto {
    private Long id;
    private String name;
    private List<Order> ordersList;
}
