package mate.academy.dto.response;

import lombok.Getter;
import lombok.Setter;
import mate.academy.model.Owner;

@Getter
@Setter
public class CarResponseDto {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String number;
    private Owner owner;
}
