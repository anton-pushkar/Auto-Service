package autoservise.controller;

import autoservise.dto.mapper.OrderMapper;
import autoservise.dto.mapper.OwnerMapper;
import autoservise.dto.request.OwnerRequestDto;
import autoservise.dto.request.create.OwnerRequestDtoForCreate;
import autoservise.dto.response.OrderResponseDto;
import autoservise.dto.response.OwnerResponseDto;
import autoservise.model.Owner;
import autoservise.service.impl.OwnerServiceImpl;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerMapper mapper;
    private final OwnerServiceImpl service;
    private final OrderMapper orderMapper;

    public OwnerController(OwnerMapper mapper, OwnerServiceImpl service, OrderMapper orderMapper) {
        this.mapper = mapper;
        this.service = service;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    @ApiOperation(value = "create owner")
    public OwnerResponseDto create(@RequestBody OwnerRequestDtoForCreate dto) {
        Owner owner = mapper.toModelForCreate(dto);
        service.create(owner);
        return mapper.toResponseDto(owner);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "update owner by id")
    public OwnerResponseDto update(@PathVariable Long id,
                                   @RequestBody OwnerRequestDto dto) {
        Owner owner = mapper.toModel(dto);
        owner.setId(id);
        service.update(owner);
        return mapper.toResponseDto(owner);
    }

    @GetMapping("/orders/{id}")
    @ApiOperation(value = "get owners orders by id")
    public List<OrderResponseDto> getOrderListByUserId(@PathVariable Long id) {
        return service.getOrdersByOwnerId(id).stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
