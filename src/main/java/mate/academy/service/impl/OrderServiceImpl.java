package mate.academy.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import mate.academy.model.Favor;
import mate.academy.model.Goods;
import mate.academy.model.Order;
import mate.academy.model.OrderStatus;
import mate.academy.repository.OrderRepository;
import mate.academy.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private static final double DIAGNOSTIC_COST = 500;
    private static final double MAX_FAVOR_DISCOUNT = 30;
    private static final double MAX_GOODS_DISCOUNT = 15;
    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order create(Order order) {
        return repository.save(order);
    }

    @Override
    public Order addGoodsToOrder(Long id, Goods goods) {
        Order order = repository.getById(id);
        order.getGoodsList().add(goods);
        return repository.save(order);
    }

    @Override
    public Order update(Order order) {
        return repository.save(order);
    }

    @Override
    public Order changeStatusById(Long id, String status) {
        Order order = repository.getById(id);
        if (status.equals("UNSUCCESSFULLY_FINISHED")
                || status.equals("SUCCESSFULLY_FINISHED")) {
            order.setFinishedTime(LocalDateTime.now());
        }
        order.setStatus(OrderStatus.valueOf(status));
        return order;
    }

    @Override
    public double getOrderCostById(Long id) {
        Order order = repository.getById(id);
        double finishCost = 0;
        int countOfOrders = order.getCar().getOwner().getOrders().size();
        if (order.getStatus().equals(OrderStatus.UNSUCCESSFULLY_FINISHED)) {
            finishCost += DIAGNOSTIC_COST;
        }
        double costOfFavor = getCostOfFavor(order.getFavorList(), countOfOrders);
        double costOfGoods = getCostOfGoods(order.getGoodsList(), countOfOrders);
        finishCost += costOfFavor + costOfGoods;
        return finishCost;
    }

    private double getCostOfFavor(List<Favor> favorList, int countOfOrders) {
        double favorsCost = 0;
        for (Favor favor : favorList) {
            if (countOfOrders * 2 < MAX_FAVOR_DISCOUNT) {
                favorsCost += favor.getCost() - (favor.getCost() * countOfOrders * 2 / 100);
            } else {
                favorsCost += favor.getCost() - (favor.getCost() * MAX_FAVOR_DISCOUNT);
            }
        }
        return favorsCost;
    }

    private double getCostOfGoods(List<Goods> goodsList, int countOfOrders) {
        double goodsCost = 0;
        for (Goods goods : goodsList) {
            if (countOfOrders < MAX_GOODS_DISCOUNT) {
                goodsCost += goods.getCost() - (goods.getCost() * countOfOrders / 100);
            } else {
                goodsCost += goods.getCost() - (goods.getCost() * MAX_GOODS_DISCOUNT / 100);
            }
        }
        return goodsCost;
    }
}
