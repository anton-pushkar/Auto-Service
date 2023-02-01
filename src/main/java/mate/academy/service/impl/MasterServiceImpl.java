package mate.academy.service.impl;

import java.util.List;
import mate.academy.model.Favor;
import mate.academy.model.Master;
import mate.academy.model.MasterSalaryStatus;
import mate.academy.model.Order;
import mate.academy.repository.MasterRepository;
import mate.academy.service.MasterService;
import org.springframework.stereotype.Service;

@Service
public class MasterServiceImpl implements MasterService {
    private static final double PERCENTAGE_OF_MASTER_SALARY = 40;
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
        Master master = repository.getReferenceById(id);
        return master.getOrdersList();
    }

    @Override
    public double getMasterSalaryById(Long id) {
        double masterSalary = 0;
        Master master = repository.getReferenceById(id);
        List<Order> masterOrdersList = master.getOrdersList();
        for (Order order : masterOrdersList) {
            List<Favor> favorList = order.getFavorList();
            for (Favor favor : favorList) {
                if (favor.getMasterStatus().equals(MasterSalaryStatus.UNPAID)) {
                    masterSalary += favor.getCost() * PERCENTAGE_OF_MASTER_SALARY / 100;
                    favor.setMasterStatus(MasterSalaryStatus.PAID);
                }
            }
        }
        return masterSalary;
    }
}
