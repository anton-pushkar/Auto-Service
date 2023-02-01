package mate.academy.service.impl;

import mate.academy.model.Goods;
import mate.academy.repository.GoodsRepository;
import mate.academy.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository repository;

    public GoodsServiceImpl(GoodsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Goods add(Goods goods) {
        return repository.save(goods);
    }

    @Override
    public Goods update(Goods goods) {
        return repository.save(goods);
    }
}
