package autoservise.service.impl;

import autoservise.model.Favor;
import autoservise.model.Master;
import autoservise.model.MasterSalaryStatus;
import autoservise.model.Order;
import autoservise.repository.MasterRepository;
import autoservise.service.MasterService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MasterServiceImpl implements MasterService {
    private static final BigDecimal PERCENTAGE_OF_MASTER_SALARY = new BigDecimal(40);
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private final MasterRepository repository;

    public MasterServiceImpl(MasterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Master create(Master master) {
        return repository.save(master);
    }

    @Override
    public Master update(Master master) {
        return repository.save(master);
    }

    @Override
    public List<Order> getMasterOrdersById(Long id) {
        Master master = repository.getById(id);
        return master.getOrdersList();
    }

    @Override
    public BigDecimal getMasterSalaryById(Long id) {
        BigDecimal masterSalary = new BigDecimal(0);
        Master master = repository.getById(id);
        List<Order> masterOrdersList = master.getOrdersList();
        for (Order order : masterOrdersList) {
            List<Favor> favorList = order.getFavorList();
            for (Favor favor : favorList) {
                if (favor.getMasterStatus().equals(MasterSalaryStatus.UNPAID)) {
                    masterSalary = masterSalary.add(favor.getCost()
                            .multiply(PERCENTAGE_OF_MASTER_SALARY).divide(ONE_HUNDRED));
                    favor.setMasterStatus(MasterSalaryStatus.PAID);
                }
            }
        }
        return masterSalary;
    }
}
