package autoservise.service.impl;

import autoservise.model.Favor;
import autoservise.model.MasterSalaryStatus;
import autoservise.repository.FavorRepository;
import autoservise.service.FavorService;
import org.springframework.stereotype.Service;

@Service
public class FavorServiceImpl implements FavorService {
    private final FavorRepository repository;

    public FavorServiceImpl(FavorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Favor create(Favor favor) {
        favor.setMasterStatus(MasterSalaryStatus.UNPAID);
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
    public Favor changeStatus(Favor favor, String status) {
        favor.setMasterStatus(MasterSalaryStatus.valueOf(status));
        return favor;
    }
}
