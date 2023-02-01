package mate.academy.service.impl;

import java.util.List;
import mate.academy.model.Order;
import mate.academy.model.Owner;
import mate.academy.repository.OwnerRepository;
import mate.academy.service.OwnerService;
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
        Owner owner = repository.getReferenceById(id);
        return owner.getOrders();
    }
}
