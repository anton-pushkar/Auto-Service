package mate.academy.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mate.academy.model.Car;
import mate.academy.model.Favor;
import mate.academy.model.Goods;

@Getter
@Setter
public class OrderRequestDtoForCreate {
    private Car car;
    private String description;
    private List<Favor> favorList;
    private List<Goods> goodsList;
}
