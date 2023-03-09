package mate.academy.dto.request;

import lombok.Data;
import mate.academy.model.Owner;

@Data
public class CarRequestDto {
    private String brand;
    private String model;
    private Integer year;
    private String number;
    private Owner owner;
}
