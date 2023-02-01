package mate.academy.service;

import mate.academy.model.Goods;
import mate.academy.model.Order;

public interface OrderService {
    Order create(Order order);

    Order addGoodsToOrder(Long id,Goods goods);

    Order update(Order order);

    Order changeStatusById(Long id, String status);

    double getOrderCostById(Long id);
}
