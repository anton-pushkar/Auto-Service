package mate.academy.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.model.Master;
import mate.academy.model.Order;

public interface MasterService {
    Master create(Master master);

    Master update(Master master);

    List<Order> getMasterOrdersById(Long id);

    BigDecimal getMasterSalaryById(Long id);
}
