package autoservise.dto.mapper;

import autoservise.dto.request.GoodsRequestDto;
import autoservise.dto.request.create.GoodsRequestDtoForCreate;
import autoservise.dto.response.GoodsResponseDto;
import autoservise.model.Goods;
import autoservise.model.Order;
import autoservise.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GoodsMapper {
    private final OrderRepository orderRepository;

    public GoodsMapper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Goods toModel(GoodsRequestDto goodsRequestDto) {
        Goods goods = new Goods();
        goods.setCost(goodsRequestDto.getCost());
        goods.setName(goodsRequestDto.getName());
        return goods;
    }

    public GoodsResponseDto toResponseDto(Goods goods) {
        GoodsResponseDto dto = new GoodsResponseDto();
        dto.setCost(goods.getCost());
        dto.setName(goods.getName());
        dto.setId(goods.getId());
        return dto;
    }

    public Goods toModelForCreate(GoodsRequestDtoForCreate goodsRequestDto) {
        Goods goods = new Goods();
        goods.setCost(goodsRequestDto.getCost());
        goods.setName(goodsRequestDto.getName());
        Order order = orderRepository.getById(goodsRequestDto.getOrderId());
        if (order.getGoodsList() == null) {
            order.setGoodsList(new ArrayList<>());
            List<Goods> goodsList = order.getGoodsList();
            goodsList.add(goods);
            order.setGoodsList(goodsList);
        } else {
            List<Goods> goodsList = order.getGoodsList();
            goodsList.add(goods);
            order.setGoodsList(goodsList);
        }
        return goods;
    }
}
