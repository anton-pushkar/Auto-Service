package mate.academy.controller;

import io.swagger.annotations.ApiOperation;
import mate.academy.dto.mapper.GoodsMapper;
import mate.academy.dto.request.GoodsRequestDto;
import mate.academy.dto.response.GoodsResponseDto;
import mate.academy.model.Goods;
import mate.academy.service.GoodsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsMapper mapper;
    private final GoodsService service;

    public GoodsController(GoodsMapper mapper, GoodsService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "create goods")
    public GoodsResponseDto create(@RequestBody GoodsRequestDto dto) {
        Goods goods = mapper.toModel(dto);
        service.add(goods);
        return mapper.toResponseDto(goods);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update goods by id")
    public GoodsResponseDto update(@PathVariable Long id,
                                   @RequestBody GoodsRequestDto dto) {
        Goods goods = mapper.toModel(dto);
        goods.setId(id);
        service.update(goods);
        return mapper.toResponseDto(goods);
    }
}
