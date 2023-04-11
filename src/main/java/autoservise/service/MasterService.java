package autoservise.service;

import autoservise.model.Master;
import autoservise.model.Order;
import java.math.BigDecimal;
import java.util.List;

public interface MasterService {
    Master create(Master master);

    Master update(Master master);

    List<Order> getMasterOrdersById(Long id);

    BigDecimal getMasterSalaryById(Long id);
}
