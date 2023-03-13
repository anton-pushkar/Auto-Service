package mate.academy.service;

import java.util.List;
import mate.academy.model.Car;
import mate.academy.model.Favor;
import mate.academy.model.Goods;
import mate.academy.model.Order;
import mate.academy.model.OrderStatus;

public interface OrderService {
    Order create(Order order);

    Order addGoodsToOrder(Long id,Goods goods);

    Order update(Order order);

    Order changeStatusById(Long id, String status);

    double getOrderCostById(Long id);

    double getOrderCostForCreate(Car car, OrderStatus status,
                                 List<Favor> favorList, List<Goods> goodsList);
}
