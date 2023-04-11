package autoservise.service;

import autoservise.model.Goods;

public interface GoodsService {
    Goods add(Goods goods);

    Goods update(Goods goods);
}
