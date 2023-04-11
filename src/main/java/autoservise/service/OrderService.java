package autoservise.service;

import autoservise.model.Car;
import autoservise.model.Favor;
import autoservise.model.Goods;
import autoservise.model.Order;
import autoservise.model.OrderStatus;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order create(Order order);

    Order addGoodsToOrder(Long id, Goods goods);

    Order update(Order order);

    Order changeStatusById(Long id, String status);

    BigDecimal getOrderCostById(Long id);

    BigDecimal getOrderCostForCreate(Car car, OrderStatus status,
                                     List<Favor> favorList, List<Goods> goodsList);
}
