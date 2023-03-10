package mate.academy.service;

import java.util.List;
import mate.academy.model.Master;
import mate.academy.model.Order;

public interface MasterService {
    Master create(Master master);

    Master update(Master master);

    List<Order> getMasterOrdersById(Long id);

    double getMasterSalaryById(Long id);
}
