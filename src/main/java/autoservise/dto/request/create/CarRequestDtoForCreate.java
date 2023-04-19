package autoservise.dto.request.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDtoForCreate {
    private String brand;
    private String model;
    private Integer year;
    private String number;
    private Long ownerId;
}
