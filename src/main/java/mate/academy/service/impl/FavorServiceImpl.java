package mate.academy.service.impl;

import mate.academy.model.Favor;
import mate.academy.model.MasterSalaryStatus;
import mate.academy.repository.FavorRepository;
import mate.academy.service.FavorService;
import org.springframework.stereotype.Service;

@Service
public class FavorServiceImpl implements FavorService {
    private final FavorRepository repository;

    public FavorServiceImpl(FavorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Favor create(Favor favor) {
        return repository.save(favor);
    }

    @Override
    public Favor update(Favor favor) {
        return repository.save(favor);
    }

    @Override
    public Favor getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Favor changeStatus(Favor favor) {
        if (favor.getMasterStatus().equals(MasterSalaryStatus.UNPAID)) {
            favor.setMasterStatus(MasterSalaryStatus.PAID);
        } else {
            favor.setMasterStatus(MasterSalaryStatus.UNPAID);
        }
        return favor;
    }
}
