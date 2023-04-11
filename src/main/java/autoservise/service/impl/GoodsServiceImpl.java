package autoservise.service.impl;

import autoservise.model.Goods;
import autoservise.repository.GoodsRepository;
import autoservise.service.GoodsService;
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
