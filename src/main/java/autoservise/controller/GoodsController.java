package autoservise.controller;

import autoservise.dto.mapper.GoodsMapper;
import autoservise.dto.request.GoodsRequestDto;
import autoservise.dto.request.create.GoodsRequestDtoForCreate;
import autoservise.dto.response.GoodsResponseDto;
import autoservise.model.Goods;
import autoservise.service.GoodsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public GoodsResponseDto create(@RequestBody GoodsRequestDtoForCreate dto) {
        Goods goods = mapper.toModelForCreate(dto);
        service.add(goods);
        return mapper.toResponseDto(goods);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "update goods by id")
    public GoodsResponseDto update(@PathVariable Long id,
                                   @RequestBody GoodsRequestDto dto) {
        Goods goods = mapper.toModel(dto);
        goods.setId(id);
        service.update(goods);
        return mapper.toResponseDto(goods);
    }
}
