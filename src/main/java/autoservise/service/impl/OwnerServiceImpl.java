package autoservise.service.impl;

import autoservise.model.Order;
import autoservise.model.Owner;
import autoservise.repository.OwnerRepository;
import autoservise.service.OwnerService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository repository;

    public OwnerServiceImpl(OwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Owner create(Owner owner) {
        return repository.save(owner);
    }

    @Override
    public Owner update(Owner owner) {
        return repository.save(owner);
    }

    @Override
    public List<Order> getOrdersByOwnerId(Long id) {
        Owner owner = repository.getById(id);
        return owner.getOrders();
    }
}
