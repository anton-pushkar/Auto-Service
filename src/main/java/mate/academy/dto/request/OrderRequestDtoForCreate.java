package mate.academy.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDtoForCreate {
    private Long carId;
    private String description;
    private List<Long> favorsId;
    private List<Long> goodsId;
}
