package autoservise.dto.request.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDtoForCreate {
    private Long carId;
    private String description;
    private String masterName;
    private Long ownerId;
}
