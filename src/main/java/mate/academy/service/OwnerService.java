package mate.academy.service;

import java.util.List;
import mate.academy.model.Order;
import mate.academy.model.Owner;

public interface OwnerService {
    Owner create(Owner owner);

    Owner update(Owner owner);

    List<Order> getOrdersByOwnerId(Long id);
}
