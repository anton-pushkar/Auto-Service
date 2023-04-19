package autoservise.service.impl;

import autoservise.model.Car;
import autoservise.model.Favor;
import autoservise.model.Goods;
import autoservise.model.Order;
import autoservise.model.OrderStatus;
import autoservise.repository.GoodsRepository;
import autoservise.repository.OrderRepository;
import autoservise.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private static final BigDecimal DIAGNOSTIC_COST = new BigDecimal(500);
    private static final double MAX_FAVOR_DISCOUNT = 30;
    private static final double MAX_GOODS_DISCOUNT = 15;
    private final OrderRepository repository;
    private final GoodsRepository goodsRepository;

    public OrderServiceImpl(OrderRepository repository, GoodsRepository goodsRepository) {
        this.repository = repository;
        this.goodsRepository = goodsRepository;
    }

    @Override
    public Order create(Order order) {
        return repository.save(order);
    }

    @Override
    public Order addGoodsToOrder(Long id, Goods goods) {
        Order order = repository.getById(id);
        goodsRepository.save(goods);
        order.getGoodsList().add(goods);
        order.setCost(getOrderCostForMapper(order.getCar(),
                order.getStatus(),
                order.getFavorList(),
                order.getGoodsList()));
        return repository.save(order);
    }

    @Override
    public Order update(Order order) {
        order.setCost(getOrderCostForMapper(order.getCar(),
                order.getStatus(),
                order.getFavorList(),
                order.getGoodsList()));
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
        order.setCost(getOrderCostForMapper(order.getCar(),
                order.getStatus(),
                order.getFavorList(),
                order.getGoodsList()));
        return repository.save(order);
    }

    @Override
    public BigDecimal getOrderCostById(Long id) {
        Order order = repository.getById(id);
        BigDecimal finishCost = BigDecimal.valueOf(0);
        int countOfOrders = order.getCar().getOwner().getOrders().size();
        if (order.getStatus().equals(OrderStatus.UNSUCCESSFULLY_FINISHED)) {
            finishCost = finishCost.add(DIAGNOSTIC_COST);
        }
        BigDecimal costOfFavor = getCostOfFavor(order.getFavorList(), countOfOrders);
        BigDecimal costOfGoods = getCostOfGoods(order.getGoodsList(), countOfOrders);
        finishCost = finishCost.add(costOfFavor).add(costOfGoods);
        order.setCost(finishCost);
        repository.save(order);
        return finishCost;
    }

    @Override
    public BigDecimal getOrderCostForMapper(Car car, OrderStatus status,
                                            List<Favor> favorList, List<Goods> goodsList) {
        BigDecimal finishCost = BigDecimal.valueOf(0);
        int countOfOrders = car.getOwner().getOrders().size();
        if (status.equals(OrderStatus.UNSUCCESSFULLY_FINISHED)) {
            finishCost = finishCost.add(DIAGNOSTIC_COST);
        }
        BigDecimal costOfFavor = getCostOfFavor(favorList, countOfOrders);
        BigDecimal costOfGoods = getCostOfGoods(goodsList, countOfOrders);
        finishCost = finishCost.add(costOfFavor).add(costOfGoods);
        return finishCost;
    }

    @Override
    public BigDecimal getOrderCostForCreate(Car car, OrderStatus status) {
        BigDecimal finishCost = BigDecimal.valueOf(0);
        int countOfOrders = car.getOwner().getOrders().size();
        if (status.equals(OrderStatus.UNSUCCESSFULLY_FINISHED)) {
            finishCost = finishCost.add(DIAGNOSTIC_COST);
        }
        return finishCost;
    }

    private BigDecimal getCostOfFavor(List<Favor> favorList, int countOfOrders) {
        BigDecimal favorsCost = new BigDecimal(0);
        if (favorList != null) {
            for (Favor favor : favorList) {
                if (countOfOrders * 2 < MAX_FAVOR_DISCOUNT) {
                    favorsCost = favorsCost.add(favor.getCost().subtract(favor.getCost()
                            .multiply(BigDecimal.valueOf(countOfOrders))
                            .multiply(BigDecimal.valueOf(2)).divide(BigDecimal.valueOf(100))));
                } else {
                    favorsCost = favorsCost.add(favor.getCost()
                            .subtract(favor.getCost()
                                    .multiply(BigDecimal.valueOf(MAX_FAVOR_DISCOUNT))));
                }
            }
        }
        return favorsCost;
    }

    private BigDecimal getCostOfGoods(List<Goods> goodsList, int countOfOrders) {
        BigDecimal goodsCost = BigDecimal.valueOf(0);
        if (goodsList != null) {
            for (Goods goods : goodsList) {
                if (countOfOrders < MAX_GOODS_DISCOUNT) {
                    goodsCost = goodsCost.add(goods.getCost().subtract(goods.getCost()
                            .multiply(BigDecimal.valueOf(countOfOrders)
                                    .divide(BigDecimal.valueOf(100)))));
                } else {
                    goodsCost = goodsCost.add(goods.getCost().subtract(goods.getCost()
                            .multiply(BigDecimal.valueOf(MAX_GOODS_DISCOUNT)
                                    .divide(BigDecimal.valueOf(100)))));
                }
            }
        }
        return goodsCost;
    }
}
