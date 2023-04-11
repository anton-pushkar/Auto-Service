package autoservise.service;

import autoservise.model.Order;
import autoservise.model.Owner;
import java.util.List;

public interface OwnerService {
    Owner create(Owner owner);

    Owner update(Owner owner);

    List<Order> getOrdersByOwnerId(Long id);
}
