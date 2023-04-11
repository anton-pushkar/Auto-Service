package autoservise.dto.mapper;

import autoservise.dto.request.GoodsRequestDto;
import autoservise.dto.response.GoodsResponseDto;
import autoservise.model.Goods;
import org.springframework.stereotype.Component;

@Component
public class GoodsMapper {
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
}
