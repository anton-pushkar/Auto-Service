package mate.academy.dto.request;

import lombok.Getter;
import lombok.Setter;
import mate.academy.model.Owner;

@Getter
@Setter
public class CarRequestDto {
    private String brand;
    private String model;
    private Integer year;
    private String number;
    private Owner owner;
}
